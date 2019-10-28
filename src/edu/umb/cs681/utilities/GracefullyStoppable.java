package edu.umb.cs681.hw12;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

class GracefullyStoppable {

//     private Path dirPath = Paths.get(".", "src", "edu", "umb", "cs681", "hw12", "resources");
//     private AccessCounter accessCounter;

//     private Runnable methodToRun = () -> {
//         Path file = getRandomFile();
//         accessCounter.increment(file);
//         int count = accessCounter.getCount(file);
//         System.out.printf("\tRequestHandler %d: file %s returned for the %d%s time\n",
//                           getThread().getId(),
//                           file.getFileName(), count,
//                           count==1?"st":count==2?"nd":"th");
//     };

//     protected RequestHandler() {
//         super();
//         accessCounter = new AccessCounter();
//         // lock = new ReentrantLock();
//     }

//     protected Runnable getMethodToRun() {
//         return methodToRun;
//     }


//     private Path getRandomFile() {
//         List<Path> files = listFiles();
//         Random rand = new Random();
//         return files.get(rand.nextInt(files.size()));
//     }
    
    
//     private List<Path> listFiles() {
//         List<Path> result = new ArrayList<>();
//         try (DirectoryStream<Path> stream =
//              Files.newDirectoryStream(dirPath, "*")) {
//             stream.forEach((entry) -> result.add(entry));
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }
//         return result;
//     }
    
// }

















// // class RequestHandler implements Runnable {

// //     private boolean done;
// //     private ReentrantLock lock;
// //     // private Path dirPath = Paths.get(".");
// //     private Path dirPath = Paths.get(".", "src", "edu", "umb", "cs681", "hw12", "resources");
// //     private AccessCounter accessCounter;

// //     protected RequestHandler() {
// //         accessCounter = new AccessCounter();
// //         lock = new ReentrantLock();
// //     }


// //     public void run() {
// //         while (true) {
// //             setLock();
// //             try {
// //                 if (done) {
// //                     System.out.printf("\tRequestHandler %d: stopping\n", getThread().getId());
// //                     break;
// //                 }
// //             } finally {
// //                 unsetLock();
// //             };
// //             Path file = getRandomFile();
// //             accessCounter.increment(file);
// //             int count = accessCounter.getCount(file);
// //             System.out.printf("\tRequestHandler %d: file %s returned for the %d%s time\n",
// //                               getThread().getId(),
// //                               file.getFileName(), count,
// //                               count==1?"st":"th");
// //             try {
// //                 Thread.sleep(100);            
// //             } catch (InterruptedException e) {
// //                 continue;
// //             }
// //         }
// //     }


// //     private Path getRandomFile() {
// //         List<Path> files = listFiles();
// //         Random rand = new Random();
// //         return files.get(rand.nextInt(files.size()));
// //     }
    
    
// //     private List<Path> listFiles() {
// //         List<Path> result = new ArrayList<>();
// //         try (DirectoryStream<Path> stream =
// //              Files.newDirectoryStream(dirPath, "*")) {
// //             stream.forEach((entry) -> result.add(entry));
// //         } catch (Exception ex) {
// //             ex.printStackTrace();
// //         }
// //         return result;
// //     }

    
// //     protected void setLock() {
// //         lock.lock();
// //     }

// //     protected void unsetLock() {
// //         lock.unlock();
// //     }
    

// //     public Thread getThread() {
// //         return Thread.currentThread();
// //     }

// //     public void gracefulStop() {
// //         setLock();
// //         try {
// //             done = true;
// //             getThread().interrupt();
// //         } finally {
// //             unsetLock();
// //         }
// //     }
    
}
