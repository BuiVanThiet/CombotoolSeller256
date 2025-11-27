package com.example.combotoolseller.ClearJar;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
public class FolderDeleter {
    public static void main(String[] args) {
        String originalPathString = "E:\\FolderTool256\\ComboHideMyAccMaHoa\\Output";

        // --- BƯỚC SỬA LỖI MAX_PATH ---
        // 1. Chuyển đổi đường dẫn sang dạng tuyệt đối (nếu chưa phải)
        String absolutePath = Paths.get(originalPathString).toAbsolutePath().toString();

        // 2. Thêm tiền tố UNC dài (UNC Long Path Prefix)
        // Lưu ý: Windows yêu cầu phải dùng / hoặc \\ và path tuyệt đối
        if (absolutePath.startsWith("E:")) {
            // Thêm \\?\ vào đầu đường dẫn (áp dụng cho đường dẫn có ổ đĩa)
            absolutePath = "\\\\?\\" + absolutePath;
        }
        // -----------------------------

        Path folderPath = Paths.get(absolutePath);

        try {
            if (Files.exists(folderPath)) {
                deleteFolder(folderPath); // Gọi hàm xóa đã sửa
                System.out.println("Thư mục đã được xóa thành công.");
            } else {
                System.out.println("Thư mục không tồn tại.");
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi xóa: " + e.getMessage());
        }
    }

    /**
     * Xóa thư mục và tất cả nội dung bên trong (xóa đệ quy).
     * * @param path Đường dẫn đến thư mục cần xóa.
     * @throws IOException Nếu có lỗi trong quá trình thao tác file (ví dụ: không có quyền).
     */
    public static void deleteFolder(Path path) throws IOException {
        // 1. Dùng Files.walk để duyệt qua tất cả các mục (kể cả thư mục con)
        try (var walk = Files.walk(path)) {

            // 2. Sắp xếp ngược lại: đảm bảo file và thư mục con được xóa trước
            // Comparator.reverseOrder() đảo ngược thứ tự, nên file/folder sâu nhất được liệt kê trước.
            // Sau đó, dùng forEach để thực hiện Files.delete(path)
            walk.sorted(Comparator.reverseOrder())
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                            // System.out.println("Đã xóa: " + p); // Gỡ comment để xem chi tiết
                        } catch (IOException e) {
                            // Ném ngoại lệ ra ngoài để xử lý lỗi
                            throw new RuntimeException("Không thể xóa " + p.toString(), e);
                        }
                    });
        } catch (RuntimeException e) {
            // Unwrap RuntimeException và ném lại IOException gốc
            throw new IOException("Không thể xóa thư mục gốc: " + e.getMessage(), e.getCause());
        }
    }
}
