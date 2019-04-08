# Demo issue spring data couchbase concurrency

The following Couchbase Server version was used for this demo: `Community Edition 6.0.0 build 1693`

### Steps to reproduce the issue
1. Create a bucket called `test`, add an admin user to it named `test` and set the password `password`.
2. Create an index for the bucket by using the following statement
    ```CREATE PRIMARY INDEX `#primary-test` ON `test` WITH {  "nodes":[ "127.0.0.1:8091" ] }```
3. Inside the bucket, create two documents with the following ids: `settings1` and `settings2`
4. `settings1` document content:
    ```
    {
         "_class": "com.example.Settings",
         "property": "VALUE1"
    }
    ```

5. `settings1` document content:
    ```
    {
         "_class": "com.example.Settings",
         "property": "VALUE2"
    }
    ```

6. Run the application `DemoIssueSpringDataCouchbaseApplication`, you should see a runtime exception
