package org.CosmosConnection;

import com.azure.cosmos.*;
import com.azure.cosmos.models.*;
import com.azure.cosmos.util.CosmosPagedIterable;

import java.io.IOException;
import java.util.List;

public class cosmosConnection {
    private static final String ENDPOINT = "https://rap7orcosmos.documents.azure.com:443/";
    private static final String KEY = "2DAbhDLArcNthNlfRW4dq8vNRoCzgWpDL6ySIh8TLa6VNzWz7G1fmAzx0iCR1Cm2Nh3fmbqwbSOpACDbPhi5Jg==";
    private static final String DATABASE_NAME = "ToDoList";
    private static final String CONTAINER_NAME = "Items";

    public static List<String> industriesNameList = null;

    public static String getValueFromCosmos;

    public static void runCosmosDB() throws IOException {
        cosmosConnection obj = new cosmosConnection();
        obj.main(new String[]{});
    }

    public static <CosmosItemProperties> void main(String[] args) {
        CosmosClientBuilder clientBuilder = new CosmosClientBuilder()
                .endpoint(ENDPOINT)
                .key(KEY);

        CosmosClient client = clientBuilder.buildClient();

        CosmosDatabase database = client.getDatabase(DATABASE_NAME);
        CosmosContainer container = database.getContainer(CONTAINER_NAME);

        String query = "SELECT * FROM Items";
        CosmosQueryRequestOptions options = new CosmosQueryRequestOptions();

        CosmosPagedIterable<TodoItem> queryResults = container.queryItems(query, options, TodoItem.class);
        for (TodoItem itemProperties : queryResults) {
            System.out.println(itemProperties.getLastName());
            //industriesNameList.add(itemProperties.getLastName());
            getValueFromCosmos = itemProperties.getLastName();
        }
        System.out.println("Getting first index ::: " + getValueFromCosmos);
        client.close();
    }


}
