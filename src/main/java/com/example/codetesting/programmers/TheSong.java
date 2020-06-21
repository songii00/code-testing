package com.example.codetesting.programmers;


import java.time.Duration;
import java.time.LocalTime;

/**
 * 프로그래머스 level 2 [3차] 방금그곡
 */
public class TheSong {

    private class Music {
        private LocalTime startTime;
        private LocalTime endTime;
        private String title;
        private String score;

        Music(String musicinfo){
            String[] music = musicinfo.split(",");
            this.startTime = getTimeBy(music[0]);
            this.endTime = getTimeBy(music[1]);
            this.title = music[2];
            this.score = replaceScore( music[3]);
        }

        Duration betweenTime(){
            return Duration.between(this.startTime,this.endTime);
        }

        private LocalTime getTimeBy(String time){
            return LocalTime.of(Integer.parseInt(time.split(":")[0]),
                    Integer.parseInt(time.split(":")[1]));
        }

        /**
         * 반복되는 음악 악보 가져오기
         * @return
         */
        private String getScore(){
            Duration duration = betweenTime();
            long minutes = duration.getSeconds()/60;
            StringBuilder score = new StringBuilder(this.score);

            for(int i=0;i<(minutes/(this.score.length()));i++){
                score.append(this.score);
            }
            return score.substring(0, (int) minutes);
        }

        /**
         * 주어진 악보 포함 여부 판단
         * @param score
         * @return
         */
        boolean isContain(String score){
            return this.getScore().contains(replaceScore(score));
        }

        /**
         * # 코드 변환
         * @param score
         * @return
         */
        private String replaceScore(String score){
            score = score.replace("C#", "H");
            score = score.replace("D#", "I");
            score = score.replace("F#", "J");
            score = score.replace("G#", "K");
            score = score.replace("A#", "L");
            return score;
        }
    }

    public String solution(String m, String[] musicinfos) {
        Music selectedMusic = null;
        for (String musicinfo : musicinfos) {
            Music searchedMusic = new Music(musicinfo);

            if (searchedMusic.isContain(m)) {
                if (selectedMusic == null) {
                    selectedMusic = searchedMusic;
                    continue;
                }
                if (selectedMusic.betweenTime().getSeconds() < searchedMusic.betweenTime().getSeconds()) {
                    selectedMusic = searchedMusic;
                }
            }
        }
        return selectedMusic == null ? "(None)" :  selectedMusic.title;
    }
}
