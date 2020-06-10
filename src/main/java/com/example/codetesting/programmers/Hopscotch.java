package com.example.codetesting.programmers;

import java.util.Arrays;

/**
 * 프로그래머스 level 2. 땅따먹기
 */
public class Hopscotch {

    //| 1 | 2 | 3 | 5 |
    //| 5 | 6 | 7 | 8 |
    //| 4 | 3 | 2 | 1 |

    static int solution(int[][] land) {
        for (int r = land.length - 2; r >= 0; r--) {
            for (int c = 0; c < 4; c++) {
                int maxLand = 0;

                for (int z = 0; z < 4; z++) {
                    if (c == z) {
                        continue;
                    }

                    if (land[r + 1][z] > maxLand) {
                        maxLand = land[r + 1][z];
                    }
                }
                land[r][c] = maxLand + land[r][c];
            }
        }

        return Arrays.stream(land[0]).max().getAsInt();
    }
}
