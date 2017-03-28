package chentian610.nettySocketIO;


import org.springframework.data.repository.CrudRepository;


public interface ClientInfoRepository extends CrudRepository<ClientInfo, String>{
    ClientInfo findClientByclientid(String clientId);
}