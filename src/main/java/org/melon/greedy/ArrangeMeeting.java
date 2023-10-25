package org.melon.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 会议安排问题
 * 有n个会议，每个会议都有开始时间和结束时间，求最多能参加多少个会议
 */
public class ArrangeMeeting {


    // 使用贪心算法
    public int arrangeWithGreedy(Meeting[] meetings) {
        // 1. 先将会议按照结束时间从小到大排序
        Arrays.sort(meetings, new MeetingComparator());
        // 2. 遍历会议，如果当前会议的开始时间大于上一个会议的结束时间，则安排这个会议
        int count = 0;
        // 当前会议的结束时间
        int end = 0;
        for (Meeting meeting : meetings) {
            // 如果会议时间大于上一个会议的结束时间，则安排这个会议
            if (meeting.start >= end) {
                count++;
                end = meeting.end;
            }
        }
        return count;
    }

    static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting o1, Meeting o2) {
            return o1.end - o2.end;
        }
    }

    // 暴力递归
    public int arrangeWithForce(Meeting[] meetings) {
        return process(meetings, 0, 0);
    }

    public int process(Meeting[] meetings, int done, int end) {
        if (meetings.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < meetings.length; i++) {
            if (meetings[i].start >= end) {
                max = Math.max(max, process(copyWithout(meetings, i), done + 1, meetings[i].end));
            }
        }
        return max;
    }

    public Meeting[] copyWithout(Meeting[] meetings, int index) {
        Meeting[] copy = new Meeting[meetings.length - 1];
        int i = 0;
        for (int j = 0; j < meetings.length; j++) {
            if (j != index) {
                copy[i++] = meetings[j];
            }
        }
        return copy;
    }


    static class Meeting {
        // 开始时间
        private int start;
        // 结束时间
        private int end;

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }

    private Meeting[] generateRandomMeetings(int size, int max, int min) {
        Meeting[] meetings = new Meeting[size];
        for (int i = 0; i < size; i++) {
            int start = (int) (Math.random() * (max - min) + min);
            int end = (int) (Math.random() * (max - start) + start);
            if(start == end) {
                end ++;
            }
            meetings[i] = new Meeting(start, end);
        }
        return meetings;
    }

    public static void main(String[] args) {
        ArrangeMeeting arrangeMeeting = new ArrangeMeeting();
        int count = 10000;
        for (int i = 0; i < count; i++) {
            Meeting[] meetings = arrangeMeeting.generateRandomMeetings(10, 100, 0);
            int force = arrangeMeeting.arrangeWithForce(meetings);
            int greedy = arrangeMeeting.arrangeWithGreedy(meetings);
            if (force != greedy) {
                System.out.println("Oops" + Arrays.toString(meetings));
                System.out.println("force: " + force + " greedy: " + greedy);

            }else {
                System.out.println("success!");
            }
        }
    }
}
