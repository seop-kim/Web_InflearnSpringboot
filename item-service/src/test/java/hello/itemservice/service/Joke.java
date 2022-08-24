package hello.itemservice.service;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Joke {

    static final int REFER_EXPERIENCE_VALUE = 100; // 기준 경험치
    private List<ExperienceEntity> experiences = new ArrayList<>();

    @Test
    void main() {
        // 경험치 3배, 4배, 5배 쿠폰
        ExperienceEntity entityA = new ExperienceEntity(3, ExperienceType.PLUS);
        ExperienceEntity entityB = new ExperienceEntity(4, ExperienceType.PLUS);
        ExperienceEntity entityC = new ExperienceEntity(5, ExperienceType.PLUS);

        experiences.add(entityA);
        experiences.add(entityB);
        experiences.add(entityC);

        int plusResult = experiencePlusCal(experiences);
        clearExperiences();


        // 경험치 스킬 60% 30% 10%
        entityA = new ExperienceEntity(60, ExperienceType.MULTIPLE);
        entityB = new ExperienceEntity(30, ExperienceType.MULTIPLE);
        entityC = new ExperienceEntity(10, ExperienceType.MULTIPLE);

        experiences.add(entityA);
        experiences.add(entityB);
        experiences.add(entityC);

        double multiResult = experienceMultiCal(experiences);
        clearExperiences();

        System.out.println("쿠폰의 추가 경험치는 : " + plusResult);
        System.out.println("스킬의 추가 경험치는 : " + multiResult);
        System.out.println("유저가 없은 총 경험치는 : " + (plusResult + multiResult + REFER_EXPERIENCE_VALUE));
    }

    @Test
    void ex() { // 예시 계산
        // 경험치 2배 쿠폰과 3배 쿠폰
        ExperienceEntity entityA = new ExperienceEntity(2, ExperienceType.PLUS);
        ExperienceEntity entityB = new ExperienceEntity(3, ExperienceType.PLUS);

        experiences.add(entityA);
        experiences.add(entityB);

        int plusResult = experiencePlusCal(experiences);
        clearExperiences();


        // 경험치 스킬 30% 50%
        ExperienceEntity entityC = new ExperienceEntity(30, ExperienceType.MULTIPLE);
        ExperienceEntity entityD = new ExperienceEntity(50, ExperienceType.MULTIPLE);

        experiences.add(entityC);
        experiences.add(entityD);
        double multiResult = experienceMultiCal(experiences);
        clearExperiences();

        System.out.println("쿠폰의 추가 경험치는 : " + plusResult);
        System.out.println("스킬의 추가 경험치는 : " + multiResult);
        System.out.println("유저가 없은 총 경험치는 : " + (plusResult + multiResult + REFER_EXPERIENCE_VALUE));
    }

    int experiencePlusCal(List<ExperienceEntity> calExperiences) {
        int result = 0;

        for (ExperienceEntity entity : calExperiences) {
            if (entity.getType() == ExperienceType.PLUS) {
                result += (REFER_EXPERIENCE_VALUE * entity.percent) - REFER_EXPERIENCE_VALUE;
                continue;
            }
        }
        return result;
    }

    double experienceMultiCal(List<ExperienceEntity> calExperiences) {
        double result = 100;

        for (int i = 0; i < calExperiences.size(); i++) {
            if (calExperiences.get(i).getType() == ExperienceType.MULTIPLE && i == 0) { // 첫 연산
                result = result + (REFER_EXPERIENCE_VALUE * calExperiences.get(i).getPercent() / 100);
            }

            if (calExperiences.get(i).getType() == ExperienceType.MULTIPLE && i > 0) { // 첫 연산 이후
                result += result * calExperiences.get(i).getPercent() / 100;
            }
        }

        result -= 100; // 기준 경험치는 뺀다.

        return result;
    }


    static class ExperienceEntity {
        private int percent;
        private ExperienceType type;

        public ExperienceEntity(int percent, ExperienceType type) {
            this.percent = percent;
            this.type = type;
        }

        public int getPercent() {
            return percent;
        }

        public ExperienceType getType() {
            return type;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public void setType(ExperienceType type) {
            this.type = type;
        }
    }

    enum ExperienceType { // 경험치 type
        PLUS, MULTIPLE
    }

    void clearExperiences() {
        experiences.clear();
    }

}
