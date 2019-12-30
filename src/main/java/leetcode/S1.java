package leetcode;
import java.util.*;

public class S1 {

    public int[] twoSum(int[] nums, int target) {
        // Initialize int arrar of size 2 to store answer [index1, index2]
        int[] answer = new int[2];
        // Initialize hashmap to store num and its index pair
        HashMap<Integer, Integer> map = new HashMap<>();
        // Iterate through nums and check each element
        for (int i = 0; i < nums.length; i++) {
            // Check if the map has an element equal to the diff of target and current element
            Integer val = map.get(target - nums[i]);
            if (val == null) {
                // If no match found, store current element and index to map
                map.put(nums[i], i);
            } else {
                // If match found, update answer
                answer[0] = val; // the index of val
                answer[1] = i; // the index of current element
                break; // exit the loop
            }
        }
        return answer;
    }
}
