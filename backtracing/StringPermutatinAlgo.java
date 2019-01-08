import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StringPermutatinAlgo {

	public static void main(String[] args) {
		String input = "AABC";

		//store the characters and their frequency in an input
		Map<Character, Long> characterCounts = input.chars().mapToObj(i -> (char) i)
				.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
		
		//stores the frequency of each character
		//Long[] count = characterCounts.values().toArray(new Long[0]);
		char []str = new char[characterCounts.size()];
		int []counts = new int[characterCounts.size()];
		int index = 0;
		
		for (Map.Entry<Character, Long> entry : characterCounts.entrySet()) {
			str[index] = entry.getKey();
			counts[index] = entry.getValue().intValue();
			index++;
		}
		
		//store all the possible combinations
		List<String> results = new ArrayList<>();
		
		//to store combination in between 
		//not used 'str', as it has only unique values
		char[] buffer = new char[input.length()];

		permute(counts, str, results, buffer, 0);
		
		System.out.print("possible combinations are: ");
		System.out.println(results);
	}

	private static void permute(int[] count, char []characters, List<String> result, char[] buffer,
			int level) {
		
		//add a combination to the result set after reaching lowest leaves
		//exit condition
		if (level == buffer.length) {
			result.add(new String(buffer));
			return;
		}
		
		//need to iterate left to right 
		for (int i = 0; i < count.length; i++) {
			
			//only positive values needs to recurse else go to next element
			if (count[i] > 0) {
				
				buffer[level] = characters[i];
				
				//reduce count of the number after each recursive call
				count[i]--;
				
				permute(count, characters, result, buffer, level + 1);
				
				//reset count
				count[i]++;
			}
		}
	}

}
