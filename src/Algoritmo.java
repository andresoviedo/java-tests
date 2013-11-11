public class Algoritmo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[] = new int[10];
		A[0] = -7;
		A[1] = 1;
		A[2] = 5;
		A[3] = 2;
		A[4] = -4;
		A[5] = 3;
		A[6] = 2;
		A[7] = 3;
		A[8] = 4;
		A[9] = 5;
		System.out.println(Solution.solution(A));
	}

	// you can also use imports, for example:

	static class Solution {
		public static int solution(int[] A) {
			int maxDepth = -1;
			for (int i = 0; i < A.length - 2; i++) {
				int P = i;
				int Q = i + 1;
				int R = i + 2;
				if (A[P] > A[Q] && A[Q] < A[R]) {
					int P2 = P;
					int R2 = R;
					while (P2 > 0 && A[P2 - 1] > A[Q] && A[P2 - 1] > A[P2])
						P2--;
					while (R2 < A.length - 1 && A[Q] < A[R2 + 1]
							&& A[R2 + 1] > A[R2])
						R2++;
					maxDepth = Math.max(maxDepth,
							(Math.min(A[P2] - A[Q], A[R2] - A[Q])));
				}
			}
			return maxDepth;
		}
		
		// [[7, -2, 0, 4, 2], [-1, 0, 1, 3, 1], [1, 2, 1, -1, 2], [4, 0, 0, -3, 0]]
		// Example test : WRONG ANSWER (got -1 expected 4)

		public int solution2(int[][] A) {
			int matLength = A[0].length;
			int maxLength = -1;
			for (int row = 0; row < matLength; row++) {
				for (int col = 0; col < matLength; col++) {
					while (col < matLength - 1 && A[row][col + 1] > A[row][col] &&
							row < matLength - 1 && A[row][row + 1] > A[0][row]	) {
						row++;
					}
					while (col < matLength - 1 && A[0][col + 1] > A[0][col]) {
						col++;
					}
				}
			}
			return maxLength;
		}
	}

}
