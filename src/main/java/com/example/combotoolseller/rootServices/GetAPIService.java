package com.example.combotoolseller.rootServices;

import com.example.combotoolseller.rootDTO.IpAddRestDTO;
import com.example.combotoolseller.rootEntites.IPLogin;
import com.example.combotoolseller.rootEntites.Ip256ColorGreen;
import com.example.combotoolseller.rootEntites.IpDevice;
import com.example.combotoolseller.rootEntites.TableKey;

import java.util.List;

public interface GetAPIService {
    List<TableKey> getAllKey();
    List<Ip256ColorGreen> getAllIpSuccess();
    List<IPLogin> getAllIpLogin();
    List<IpDevice> getAllIpDevice();
    void getAddIp();
    IpAddRestDTO getIpAddRest();
}
