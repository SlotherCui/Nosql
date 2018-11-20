package Model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * 单例模式连接MongoDB数据库
 */
public class MongoDBJDBC{



   public static MongoDatabase Initial(){
       return DataBaseHolder.mongoDatabase;
   }


  private static class DataBaseHolder{

      public static final MongoClient mongoClient = new MongoClient( "localhost" , 27017 );


      public static final MongoDatabase mongoDatabase = mongoClient.getDatabase("user201600301079");
   }
}