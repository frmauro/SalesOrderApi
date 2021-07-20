import org.springframework.stereotype.Service;

@Service
public class UserServiceGRPC {
     //Local PORT 
    private static final Integer PORT = 50051;

    //container PORT 
    //private static final Integer PORT = 9090;
    
    // use from local to docker container without compose
    private static final String SERVICEURL = "localhost";
    //private static final String SERVICEURL = "172.17.0.6";
    // use from container to docker container without compose
    //private static final String SERVICEURL = "salesproductapi";


    // public static SalesProductApi.ProductResponse getById(Integer id) {
    //     ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVICEURL, PORT).usePlaintext().build();
    //     SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);
    //     SalesProductApi.ProductResponse response = stub.getProduct(SalesProductApi.ProductId.newBuilder().setId(id).build());
    //     channel.shutdown();
    //     return response;
    // }
}