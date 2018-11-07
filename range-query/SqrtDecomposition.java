package ds.rangequery;

import java.util.Arrays;

public class SqrtDecomposition {
	
	public static int[] build(int []input) {
		//find block size
		int blockSize = (int) Math.ceil(Math.sqrt(input.length));
		
		//define an array equals to block size
		int []blocks = new int[blockSize];
		
		//add sum of elements in a block and store the value in an array
		for (int i = 0; i < input.length; i++) {
			blocks[i / blockSize] += input[i];
		}
		
		return blocks;
	}

	
	public static int query(int []dataset, int []blockSum, int left, int right, int blockSize) {
		//find non overlapped blocks
		int startBlock = left / blockSize;
		int endBlock = right / blockSize;
		
		int sum = 0;
		
		//sum of start block
		for (int i = left; i < ((startBlock + 1) * blockSize); i++)
			sum += dataset[i];
		
		//sum of end block
		for (int i = (endBlock * blockSize); i <= right; i++)
			sum += dataset[i];
		
		//sum of overlapped blocks
		for (int i = startBlock + 1; i < endBlock; i++)
			sum += blockSum[i];
		
		return sum;
	}
	
	public static void main(String[] args) {
		int []inp  = {1,5,2,4,6,1,3,5,7};
		int []blocksum = build(inp);
	
		System.out.println(Arrays.toString(blocksum));
		
		int sum = query(inp, blocksum, 1,6,3);
		System.out.println(sum);
	}
}
