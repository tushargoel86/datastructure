package mix;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class ForkJoinExample {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long size = getSizeUsingForkJoin();
		System.out.println("total =" + (System.currentTimeMillis() - start));
		System.out.println((size/1000000) + "MB");
	}
	
   private static long getSizeUsingForkJoin() {
    	ForkJoinPool pool = ForkJoinPool.commonPool();
    	return pool.invoke(new FileTask(new File("--Some path--")));
    }
}
class FileQueue implements Runnable {

	private BlockingQueue<File> queue;
	private AtomicLong atl;
	private Object obj;
	
	public FileQueue(BlockingQueue<File> queue, AtomicLong atl) {
		this.queue = queue;
		this.atl = atl;
	}

	@Override
	public void run() {
		System.out.println("isEmpty? " + queue.isEmpty());
		while (!queue.isEmpty()) {
			File temp = queue.poll();
			if (temp.isFile()) atl.addAndGet(temp.length());
			else {
				for(File file : temp.listFiles()) {
				 	queue.offer(file);
				}	
			}
		}
	}
	
}


class FileTask extends RecursiveTask<Long> {

	private File file;
	public FileTask(File file) {
		this.file = file;
	}
	
	@Override
	protected Long compute() {
		 List<RecursiveTask<Long>> forks = new LinkedList<>();
		if (file.isFile()) {
			FileSizeTask sizeTask = new FileSizeTask(file);
			forks.add(sizeTask);
			sizeTask.fork();
		} else {
			for (File temp : file.listFiles()) {
				FileTask task = new FileTask(temp);
				forks.add(task);
				task.fork();
				
			}
		}

		long sum = 0L;
		for (RecursiveTask<Long> task : forks) {
			if (task != null)
				sum += task.join();	
		}  
		return sum;
	}
}

class FileSizeTask extends RecursiveTask<Long> {
	private static final long serialVersionUID = 1L;
	private final File file;
    
    FileSizeTask(File file) {
        this.file = file;
    }
    
    @Override
    protected Long compute() {
        return file.length();
    }
}
