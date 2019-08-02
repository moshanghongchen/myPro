package com.lch.my_air_pan;


import com.alibaba.fastjson.JSON;
import com.lch.my_air_pan.model.file.entity.FileBean;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import lombok.extern.log4j.Log4j;
import lombok.val;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyTest {
    @Test
    public void test1(){
        System.out.println(new BigDecimal(1).equals(null));
    }
    /** 
     * @Description: MyTest  验证
     * @Param:
     * @return:  
     * @Author: LiuCanHui 
     * @Date: 9:07 
     */
    @Test
    public void test2(){
        final val fileBean = new FileBean();
        fileBean.setOwnerId("123");
        final Document doc =objToDoc(fileBean);

        System.out.println(doc);
    }





    @Test
    public void test3()throws Exception{
        ServerAddress serverAddress = new ServerAddress("192.144.182.15", 27017);
        final MongoClient client = new MongoClient(serverAddress);
        //获取存储文件的数据库
        MongoDatabase myDatabase = client.getDatabase("file");
        final MongoCollection<Document> table = myDatabase.getCollection("fs.files");
        final BsonDocument bsonDocument = new BsonDocument();
        bsonDocument.append("metadata.ownerId",new BsonString("2"));
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
        }
        System.out.println(fileBeanList);
    }
@Test
    public void test4()throws Exception{
        ServerAddress serverAddress = new ServerAddress("192.144.182.15", 27017);
        final MongoClient client = new MongoClient(serverAddress);
        //获取存储文件的数据库
        MongoDatabase myDatabase = client.getDatabase("file");
        GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);
        final ObjectId objectId = new ObjectId("5d3fe06e2ec035029221765f");
        OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("D:/abc.txt")));
        gridFSBucket.downloadToStream(objectId,out);

    }
    @Test
    public void test41()throws Exception{
        final ObjectId objectId = new ObjectId("5d3fe06e2ec035029221765f");
        System.out.println(objectId);
    }




@Test
public void test123()throws Exception{
    final FileBean fileBean = new FileBean();
    final Field ownerId = fileBean.getClass().getDeclaredField("ownerId");
    final Method method = fileBean.getClass().getMethod("setOwnerId", String.class);
    method.invoke(fileBean,ownerId.getGenericType().getTypeName());
    System.out.println(fileBean);

}


@Test
public void tstqq()throws Exception{
    final FileBean fileBean = new FileBean();
    final Field ownerId = fileBean.getClass().getDeclaredField("ownerId");
    final Class<? extends Type> aClass = ownerId.getGenericType().getClass();
    String value="123";




}








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
