package cn.inbs.blockchain;

import cn.inbs.blockchain.common.utils.BlockUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description:
 * @Package: cn.inbs.blockchain
 * @ClassName:
 * @Date: 2018年05月18-17:49
 * @Author: createBy:zhangmingyang
 **/
public class TestBlockUtils {
    private static Logger logger = LoggerFactory.getLogger(TestBlockUtils.class);


    public static void main(String[] args) {
        testThreadBlock();
    }

    private static void testThreadBlock() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        context.start();
        ThreadPoolTaskExecutor threadPoolTaskExecutor = (ThreadPoolTaskExecutor) context.getBean("threadPoolTaskExecutor");
        List<FutureTask> futureTaskList = new ArrayList<FutureTask>();
        final Map<String, Object> bigMap = new HashMap<String, Object>();

        for (int i = 0; i < 100; i++) {
            FutureTask futureTask = new FutureTask(new Callable() {
                @Override
                public Object call() {

                    HashMap<String, Object> map = null;
                    try {
                        map = testBlock();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bigMap.putAll(map);
                    return true;
                }
            });

            futureTaskList.add(futureTask);
            threadPoolTaskExecutor.execute(futureTask);
            logger.info("第[{}]个线程开始执行", futureTask.toString());
        }

        for (int i = 0; i < futureTaskList.size(); i++) {
            try {
                Object o = futureTaskList.get(i).get();
                logger.info("第[{}]个线程执行完成", futureTaskList.get(i).toString());
            } catch (Exception e) {
                logger.error("线程错误,cause by:", e);
            }
        }

        System.err.println(bigMap.size());

        context.close();

    }


    private static HashMap<String, Object> testBlock() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 200; i++) {
            list.add("深房地字第40006019501号");
            list.add("深房地字第30007541681号");
            list.add("深圳市不动产权第00802351号");
            list.add("深圳市深圳市不动产权第00909791号");
            list.add("深圳市不动产权第00911531号");
            list.add("深房地字第30004871351号");
            list.add("深房地字第50001671571号");
            list.add("深圳市不动产权第00443931号");
            list.add("深圳市不动产权第00920331号");
            list.add("深房地字第60007017301号");
            list.add("深圳市不动产权第01177281号");
            list.add("深房地字第20004446261号");
            list.add("深圳市不动产权第00735291号");
            list.add("深圳市不动产权第00607411号");
            list.add("深圳市不动产权第00351831号");
            list.add("深圳市不动产权第01065381号");
            list.add("深房地字第60004348517号");
            list.add("深房地字第20006491691号");
            list.add("深圳市不动产权第00770611号");
        }

        for (String houseCode : list) {
            map.put(BlockUtils.createContractId(houseCode), null);
        }
        return map;
    }
}
