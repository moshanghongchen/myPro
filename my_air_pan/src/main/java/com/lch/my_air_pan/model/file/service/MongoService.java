package com.lch.my_air_pan.model.file.service;

import com.alibaba.fastjson.JSON;
import com.lch.my_air_pan.model.file.entity.FileBean;
import com.lch.my_air_pan.redis.RedisService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import lombok.val;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: MongoService
 * @Author: LiuCanHui
 * @Date: 2019/6/10
 */
@Service
public class MongoService {
   @Autowired
   RedisService redisService;

   public static MongoClient client=null;

   public void getMongoClient(){
      if(client==null){
         ServerAddress serverAddress = new ServerAddress("192.144.182.15", 27017);
         client = new MongoClient(serverAddress);
      }
   }
   public void saveFile(FileBean fileBean)throws Exception{

      fileBean.setOwnerId(redisService.get("user"));

      getMongoClient();

      MultipartFile file = fileBean.getFile();
      fileBean.setFile(null);
      //获取存储文件的数据库
      MongoDatabase myDatabase = client.getDatabase("file");

      GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);
      //创建一个文件容器（利用该对象上传下载文件）
      GridFSBucket gridFSFilesBucket = GridFSBuckets.create(myDatabase, "image_001");

      //定制容器
      GridFSUploadOptions options = new GridFSUploadOptions().chunkSizeBytes(102400).metadata(objToDoc(fileBean));
      ObjectId fileId = gridFSBucket.uploadFromStream(file.getOriginalFilename(), file.getInputStream(), options);
   }

   public boolean downLoadFile(HttpServletResponse response,String fileId)throws Exception{
      getMongoClient();
      //获取存储文件的数据库
      MongoDatabase myDatabase = client.getDatabase("file");

      GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);
      //下载
      try {
         gridFSBucket.downloadToStream(new ObjectId(fileId),response.getOutputStream());
      } catch (IOException e) {
         return false;
      }
      return true;
   }


   /**
    * @Description: MongoService
    * @Param: 通过document对象查询
    * @return:
    * @Author: LiuCanHui
    * @Date: 16:26
    */
   public List<FileBean> queryFileByDocument(Map<String,BsonString> map)throws Exception{
      ServerAddress serverAddress = new ServerAddress("192.144.182.15", 27017);
      final MongoClient client = new MongoClient(serverAddress);
      //获取存储文件的数据库
      MongoDatabase myDatabase = client.getDatabase("file");
      final MongoCollection<Document> table = myDatabase.getCollection("fs.files");
      final BsonDocument bsonDocument = new BsonDocument();
      //添加条件
      for (String key: map.keySet()) {

         bsonDocument.append(key,map.get(key));
      }

      final FindIterable<Document> documents = table.find(bsonDocument);

      final MongoCursor<Document> iterator = documents.iterator();

      List<FileBean> fileBeanList =new ArrayList<>();
      while(iterator.hasNext()) {
         final val next = iterator.next();
         System.err.println(next.toJson());
         Document metadata = (Document) next.get("metadata");
         final FileBean fileBean = JSON.parseObject(metadata.toJson(), FileBean.class);
         fileBeanList.add(fileBean);
         final Long fileSize = next.getLong("length");
         fileBean.setFileSize(fileSize);
         final Long updateTime = next.getDate("uploadDate").getTime();
         fileBean.setUpdateTime(updateTime);
         final String md5 = next.getString("md5");
         fileBean.setMd5Code(md5);
         final String fileName = next.getString("filename");
         fileBean.setFileName(fileName);
         final String fileId = next.getObjectId("_id").toString();
         fileBean.setId(fileId);
      }
      return fileBeanList;
   }

   /**
    * @Description: MongoService
    * @Param: 通过document对象查询
    * @return:
    * @Author: LiuCanHui
    * @Date: 16:26
    */
   public List<FileBean> queryFileByDbFilter(Map<String,Object> map)throws Exception{
      ServerAddress serverAddress = new ServerAddress("192.144.182.15", 27017);
      final MongoClient client = new MongoClient(serverAddress);
      //获取存储文件的数据库
      MongoDatabase myDatabase = client.getDatabase("file");
      final MongoCollection<Document> table = myDatabase.getCollection("fs.files");
      final BasicDBObject filter = new BasicDBObject();
      for (String key: map.keySet()) {
         filter.append(key,map.get(key));
      }
      final FindIterable<Document> documents = table.find(filter);
      final MongoCursor<Document> iterator = documents.iterator();
      List<FileBean> fileBeanList =new ArrayList<>();
      while(iterator.hasNext()) {
         final val next = iterator.next();
         System.err.println(next.toJson());
         Document metadata = (Document) next.get("metadata");
         final FileBean fileBean = JSON.parseObject(metadata.toJson(), FileBean.class);
         fileBeanList.add(fileBean);
         final Long fileSize = next.getLong("length");
         fileBean.setFileSize(fileSize);
         final Long updateTime = next.getDate("uploadDate").getTime();
         fileBean.setUpdateTime(updateTime);
         final String md5 = next.getString("md5");
         fileBean.setMd5Code(md5);
         final String fileName = next.getString("filename");
         fileBean.setFileName(fileName);
         final String fileId = next.getObjectId("_id").toString();
         fileBean.setId(fileId);
      }
      return fileBeanList;
   }


/**
 * @Description: MongoService
 * @Param: 通过反射将普通对象转为document对象
 * @return:
 * @Author: LiuCanHui
 * @Date: 16:21
 */
   public Document objToDoc(Object obj){
      final val document = new Document();
      try {
         //获取属性
         final Class<?> c = obj.getClass();
         final val fields = c.getDeclaredFields();
         System.err.println(fields.length);
         for (Field field:fields  ) {
            field.setAccessible(true);
            final val name = field.getName();
            Object value = field.get(obj);
            if(value==null||value.getClass()== MultipartFile.class|| Modifier.isStatic(field.getModifiers())){continue;}
            document.append(name, value);
         }
      }catch(Exception e){
         System.err.println(e);
         return null;
      }

      return document;
   }
}
