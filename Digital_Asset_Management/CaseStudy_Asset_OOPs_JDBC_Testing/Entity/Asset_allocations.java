package Entity;

import java.util.Date;

public class Asset_allocations {
    private int allocation_id;
    private int asset_id;
    private int employee_id;
    private Date allocation_date;
    private Date return_date;
    // default constructor
    Asset_allocations()
    {}
    //parametrized constructor
    public Asset_allocations(int allocation_id,int asset_id,int employee_id,Date allocation_date,Date return_date)
    {
        this.allocation_id=allocation_id;
        this.asset_id=asset_id;
        this.employee_id=employee_id;
        this.allocation_date=allocation_date;
        this.return_date=return_date;
    }
    //getters and setters
    public int getAllocation_id() {
        return allocation_id;
    }

    public void setAllocation_id(int allocation_id) {
        this.allocation_id = allocation_id;
    }

    public int getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(int asset_id) {
        this.asset_id = asset_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public Date getAllocation_date() {
        return allocation_date;
    }

    public void setAllocation_date(Date allocation_date) {
        this.allocation_date = allocation_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
}
