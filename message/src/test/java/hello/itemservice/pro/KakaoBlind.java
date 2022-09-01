package hello.itemservice.pro;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class KakaoBlind {

    /**
     * 매출 하락을 최소화하는 문제다.
     * 직원은 번호로 표기하며 1번은 CEO
     * int 형 1차원 배열은 직원을 index 값으로 표현하며 각 value 는 해당 직원의 매출이다.
     * links 는 각 직원들이 누구 산하에 있는지 표기하는 항목이다. 즉 팀장이다. 이 배열의 x 값을 팀장 y 값은 팀원이다. (links 크기는 sales 크기의 -1)
     * <p>
     * sales = 14, 17, 15, 18, 19, 14, 13, 16, 28, 17
     * links = [[10, 8], [1, 9], [9, 7], [5, 4], [1, 5], [5, 10], [10, 6], [1, 3], [10, 2]]
     * 위와 같이 주어졌을 때 답은 44이다.
     */

    // 위치는 0 부터 시작
    // length 는 1부터
    @Test
    public void methodTest1() {
        int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
        int[][] links = {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}};
        int cols = 44;

        int result = solutionTest(sales, links);
        // assertThat(cols).isEqualTo(result);
    }

    @Test
    public void methodTest2() {
        int[] sales = {5, 6, 5, 3, 4};
        int[][] links = {{2, 3}, {1, 4}, {2, 5}, {1, 2}};
        int cols = 6;

        int result = solutionTest(sales, links);
        assertThat(cols).isEqualTo(result);
    }

    @Test
    public void methodTest3() {
        int[] sales = {5, 6, 5, 1, 4};
        int[][] links = {{2, 3}, {1, 4}, {2, 5}, {1, 2}};
        int cols = 5;

        int result = solutionTest(sales, links);
        assertThat(cols).isEqualTo(result);
    }

    @Test
    public void methodTest4() {
        int[] sales = {10, 10, 1, 1};
        int[][] links = {{3, 2}, {4, 3}, {1, 4}};
        int cols = 2;

        int result = solutionTest(sales, links);
        assertThat(cols).isEqualTo(result);
    }

    // TEST Method
    int solutionTest(int[] sales, int[][] links) {
        int result = 0;
        Map<Integer, List<Integer>> teamMap = teamMapCreate(links); // 팀을 구분하여 Map 에 저장
        System.out.println("teamMap = " + teamMap);

        Collection<List<Integer>> values = teamMap.values(); // value 값을 배열로
        Set<Integer> index = teamMap.keySet(); // key 값을 배열로
        /**
         for (int i : index) {
         List<Integer> integers = teamMap.get(i);
         System.out.println("integers " + i + " : " + integers);
         int size = teamMap.get(i).size();

         for (int j = 0; j < size; j++) {
         Integer integer = teamMap.get(i).get(j);

         if (integer == i) {
         System.out.println("error! master : " + i + " , empl : " + integer);
         }
         }
         }
         */

        for (int no : sales) {
            System.out.println(no + " 직원의 매출액은 " + ㅋ);
        }

        return result;
    }


    // 직원 번호를 통해 해당 직원의 부하 직원들을 Map 으로 만들어주는 메소드
    private Map<Integer, List<Integer>> teamMapCreate(int[][] links) {
        Map<Integer, List<Integer>> emList = new HashMap<>();

        for (int i = 0; i < links.length; i++) {
            EmployeeManage em = new EmployeeManage();
            boolean isMaster = false; // 현재 팀장이 등록이 되었는지
            Integer master = 0; // 지금 확인하는 팀장의 번호를 저장하는 변수

            for (int j = 0; j < links[i].length; j++) {

                if (j == 0) { // 2차원 배열의 [x][0] 번째 값은 팀장이므로
                    if (emList.get(links[i][j]) == null) { // 현재 Map 배열에 팀장의 값으로 key 가 없으면 map 에 값을 넣을 준비를 한다.
                        em.setMy(links[i][j]);
                    } else { // 있으면 isMaster 를 true 로 바꾼다.
                        isMaster = true;
                    }
                    master = links[i][j]; // 해당 값을 master 에 저장한 후 j 를 ++ 한다.
                    continue;
                }

                // isMaster 에 따라 바뀐다. true 면 map 에서 list 를 가져와 값을 저장하고
                // false 이면 새로운 값을 저장하기 위해 em 객체의 list 에 값을 저장한다.
                if (isMaster) {
                    emList.get(master).add(links[i][j]);
                }
                if (!isMaster) {
                    em.getEmployee().add(links[i][j]);
                }
            } // for j

            if (!isMaster) {
                emList.put(em.getMy(), em.getEmployee());
            }
        }
        return emList;
    }

    // 직원을 Map 넣어보는건? 불필요한 것 같음
    public Map<Integer, Integer> employeeMapCreate(int[] sales) {
        Map<Integer, Integer> employeeMap = new LinkedHashMap<>();
        for (int i = 0; i < sales.length; i++) {
            employeeMap.put(i + 1, sales[i]);
        }
        return employeeMap;
    }

    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        return answer;
    }

    static class EmployeeManage { // 조직도
        Integer my;
        List<Integer> employee = new ArrayList<>();


        public Integer getMy() {
            return my;
        }

        public void setMy(Integer my) {
            this.my = my;
        }

        public List<Integer> getEmployee() {
            return employee;
        }
    }
}
