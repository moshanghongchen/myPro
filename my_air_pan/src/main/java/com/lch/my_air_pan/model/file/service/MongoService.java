package com.lch.my_air_pan.model.file.service;

import com.lch.my_air_pan.model.file.entity.FileBean;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: MongoService
 * @Author: LiuCanHui
 * @Date: 2019/6/10
 */
@Service
public class MongoService {
   public static MongoClient client=null;

   public void getMongoClient(){
      ServerAddress serverAddress = new ServerAddress("192.144.182.15", 27017);
      client = new MongoClient(serverAddress);
   }
   public void saveFile(FileBean fileBean)throws Exception{
      getMongoClient();

      MultipartFile file = fileBean.getFile();
      //获取存储文件的数据库
      MongoDatabase myDatabase = client.getDatabase("file");

      GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);
      //创建一个文件容器（利用该对象上传下载文件）
      GridFSBucket gridFSFilesBucket = GridFSBuckets.create(myDatabase, "image_001");

      //定制容器
      GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(102400).metadata(new Document("desc", "testimg"));
      //
      ObjectId fileId = gridFSBucket.uploadFromStream("mongodb-tutorial", file.getInputStream(), options);
      //下载
      //gridFSBucket.downloadToStream("mongodb-tutorial",new FileOutputStream("C:\\Users\\lch\\Desktop\\360\\截图\\2.png"));
   }

   public void saveFileInfo(FileBean fileBean)throws Exception{
      MongoCollection<Document> collection = client.getDatabase("lch").getCollection("fileInfo");
   //      collection
      }


}
