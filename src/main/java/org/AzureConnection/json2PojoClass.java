package org.AzureConnection;

public class json2PojoClass {
    /*
    "id": "1",
    "containernumber": 1,
    "insideMaterials": "imported shoes"
    * */

    private int id;
    private String containernumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContainernumber() {
        return containernumber;
    }

    public void setContainernumber(String containernumber) {
        this.containernumber = containernumber;
    }

    public String getInsideMaterials() {
        return insideMaterials;
    }

    public void setInsideMaterials(String insideMaterials) {
        this.insideMaterials = insideMaterials;
    }

    private String insideMaterials;


}
