package com.yl.demo;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

public class ZkClientDemo {
	//zookeeper连接地址 可用逗号分隔多个地址 127.0.0.1:2181,127.0.0.2:2181
    private static final String CONNECT_ADR="127.0.0.1:2181";

    public static void main(String[]args){

        //5000，连接超时时间
        ZkClient zkClient = new ZkClient(CONNECT_ADR,5000);

        System.out.println("Connect successfully..");

        String path="/testRoot";
        //监听节点  subscribeChildChanges 监听当前节点以及子节点增加或者删除
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("路径："+s);
                System.out.println("变更的节点为:"+list);
            }
        });
        //监听节点  subscribeDataChanges 监听当前节点以及子节点内容的变更
        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("路径："+s);
                System.out.println("变更的内容为:"+o.toString());
            }

            public void handleDataDeleted(String s) throws Exception {
                System.out.println("路径："+s);
            }
        });
        //创建节点 true表示递归创建
        zkClient.createPersistent("/testRoot/children",true);
        //修改节点信息
        zkClient.writeData("/testRoot","testRoot");
        zkClient.writeData("/testRoot","new testRoot");
        //修改子节点信息
        zkClient.writeData("/testRoot/children","testRoot children");
        //递归删除节点
        zkClient.deleteRecursive(path);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
