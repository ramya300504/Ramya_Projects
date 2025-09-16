package Entity;
import java.util.Date;
public class Assets {
    private int asset_id;
    private String asset_name;
    private String asset_type;
    private String serial_number;
    private Date purchase_date;
    private String location;
    private String status;
    private int onwer_id;
    // default constructor
    Assets() {
    }



    //parameterized constructor
    public Assets(int asset_id, String asset_name, String asset_type, String serial_number, Date purchase_date, String location, String status, int onwer_id)
    {
        this.asset_id=asset_id;
        this.asset_name=asset_name;
        this.asset_type=asset_type;
        this.serial_number=serial_number;
        this.purchase_date=purchase_date;
        this.location=location;
        this.status=status;
        this.onwer_id=onwer_id;
    }
    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getAsset_type() {
        return asset_type;
    }

    public void setAsset_type(String asset_type) {
        this.asset_type = asset_type;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOnwer_id() {
        return onwer_id;
    }

    public void setOnwer_id(int onwer_id) {
        this.onwer_id = onwer_id;
    }

    @Override
    public String toString() {
        return
                 asset_id +
                ", " + asset_name +
                ", " + asset_type +
                ", " + serial_number +
                ", " + purchase_date +
                ", " + location +
                ", " + status +
                ", " + onwer_id;
    }
}