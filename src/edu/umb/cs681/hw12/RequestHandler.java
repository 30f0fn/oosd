package edu.umb.cs681.hw12;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler extends GracefullyStoppingRunnable {

    private Path dirPath = Paths.get(".", "src", "edu", "umb", "cs681", "hw12", "resources");
    private AccessCounterInterface accessCounter;

    public RequestHandler(AccessCounterInterface counter) {
        accessCounter = counter;
    }

    private Runnable methodToRun = () -> {
        Path file = getRandomFile();
        accessCounter.increment(file);
        // int count = accessCounter.getCount(file);
        // System.out.printf("\tRequestHandler %d: file %s returned for the %d%s time\n",
        //                   Thread.currentThread().getId(),
        //                   file.getFileName(), count,
        //                   count==1?"st":count==2?"nd":"th");
    };


    protected Runnable getMethodToRun() {
        return methodToRun;
    }


    private Path getRandomFile() {
        List<Path> files = listFiles();
        Random rand = new Random();
        return files.get(rand.nextInt(files.size()));
    }
    
    
    private List<Path> listFiles() {
        List<Path> result = new ArrayList<>();
        try (DirectoryStream<Path> stream =
             Files.newDirectoryStream(dirPath, "*")) {
            stream.forEach((entry) -> result.add(entry));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
}

