package dk.via.pro3.slaughterhouse.grpc;

import dk.via.pro3.slaughterhouse.model.Product;
import dk.via.pro3.slaughterhouse.service.SlaughterhouseService;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class SlaughterhouseGrpcService
    extends SlaughterhouseServiceGrpc.SlaughterhouseServiceImplBase {

  private final SlaughterhouseService slaughterhouseService;

  public SlaughterhouseGrpcService(
      SlaughterhouseService slaughterhouseService) {
    this.slaughterhouseService = slaughterhouseService;
  }

  @Override
  public void getAnimalsByProduct(
      ProductRequest request,
      StreamObserver<AnimalNumbersResponse> responseObserver) {

    try {
      var animalNumbers =
          slaughterhouseService.getAnimalsByProduct(
              request.getProductId());

      AnimalNumbersResponse response =
          AnimalNumbersResponse.newBuilder()
              .addAllRegistrationNumbers(animalNumbers)
              .build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (Exception e) {
      e.printStackTrace();
      responseObserver.onError(e);
    }
  }

  @Override
  public void getProductsByAnimal(
      AnimalRequest request,
      StreamObserver<ProductsResponse> responseObserver) {

    var products =
        slaughterhouseService.getProductsByAnimal(
            request.getRegistrationNumber());

    ProductsResponse.Builder responseBuilder =
        ProductsResponse.newBuilder();

    for (Product product : products) {

      ProductMessage productMessage =
          ProductMessage.newBuilder()
              .setProductId(product.getProductId())
              .setName(product.getName())
              .build();

      responseBuilder.addProducts(productMessage);
    }

    responseObserver.onNext(responseBuilder.build());
    responseObserver.onCompleted();
  }
}