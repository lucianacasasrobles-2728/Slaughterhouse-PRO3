package dk.via.pro3.slaughterhouse.model;

public class Tray {

  private int trayId;
  private String partType;
  private double maxWeight;

  public Tray() {
  }

  public Tray(int trayId, String partType, double maxWeight) {
    this.trayId = trayId;
    this.partType = partType;
    this.maxWeight = maxWeight;
  }

  public int getTrayId() {
    return trayId;
  }

  public void setTrayId(int trayId) {
    this.trayId = trayId;
  }

  public String getPartType() {
    return partType;
  }

  public void setPartType(String partType) {
    this.partType = partType;
  }

  public double getMaxWeight() {
    return maxWeight;
  }

  public void setMaxWeight(double maxWeight) {
    this.maxWeight = maxWeight;
  }
}