package Entity;
import java.util.Date;
public class Maintenance_Records {
    private int maintenance_id;
    private int asset_id;
    private Date maintenance_date;
    private String description;
    private double cost;


    // default constructor
    Maintenance_Records()
    {
    }
    //parameterized constructor
    Maintenance_Records(int maintenance_id,int asset_id,Date maintenance_date,String description,double cost)
    {
        this.maintenance_id=maintenance_id;
        this.asset_id=asset_id;
        this.maintenance_date=maintenance_date;
        this.description=description;
        this.cost=cost;
    }
    //getters and setters
    public int getMaintenance_id() {
        return maintenance_id;
    }

    public void setMaintenance_id(int maintenance_id) {
        this.maintenance_id = maintenance_id;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public Date getMaintenance_date() {
        return maintenance_date;
    }

    public void setMaintenance_date(Date maintenance_date) {
        this.maintenance_date = maintenance_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
