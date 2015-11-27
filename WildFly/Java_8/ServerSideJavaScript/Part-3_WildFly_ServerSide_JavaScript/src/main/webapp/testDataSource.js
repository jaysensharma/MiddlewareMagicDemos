$undertow
    .onGet("/getCustomersViaDataSource",
        {headers: {"content-type": "application/json"}},
        ['jndi:java:jboss/MySqlDS', function ($exchange, db) {
            return db.select("select * from CUSTOMER");
        }])