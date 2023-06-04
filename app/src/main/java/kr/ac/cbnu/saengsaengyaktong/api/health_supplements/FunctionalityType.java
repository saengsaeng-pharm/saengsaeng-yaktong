package kr.ac.cbnu.saengsaengyaktong.api.health_supplements;

public enum FunctionalityType {
    OSTEOPOROSIS(FunctionalityCategory.DISEASE_RISK_REDUCTION, "1-1", "골다공증 발생 위험 감소에 도움을 줌", new String[]{"칼슘", "비타민 D"}),
    CAVITY_RISK_REDUCTION(FunctionalityCategory.DISEASE_RISK_REDUCTION, "1-2", "충치 발생 위험 감소에 도움을 줌", new String[]{"자일리톨"}),
    THYROID_HORMONE_SYNTHESIS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-1", "갑상선 호르몬 합성에 필요", new String[]{"요오드"}),
    CONNECTIVE_TISSUE_FORMATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-2", "결합조직 형성과 기능 유지에 필요", new String[]{"비타민 C"}),
    BODY_TISSUE_CONSTITUENT(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-3", "근육, 결합조직 등 신체조직의 구성 성분", new String[]{"단백질"}),
    PROTEIN_UTILIZATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-4", "단백질 및 아미노산 이용에 필요", new String[]{"비타민 B6"}),
    BONE_FORMATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-5", "뼈 형성에 필요", new String[]{"망간"}),
    BONE_AND_TEETH_FORMATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-6", "뼈와 치아 형성에 필요", new String[]{"칼슘"}),
    BONE_COMPOSITION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-7", "뼈의 구성에 필요", new String[]{"비타민 K"}),
    BONE_DEVELOPMENT_AND_MAINTENANCE(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-8", "뼈의 형성과 유지에 필요", new String[]{"비타민 D"}),
    OXIDATION_REDUCTION_ENZYME_ACTIVITY(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-9", "산화·환원 효소의 활성에 필요", new String[]{"몰리브덴"}),
    EPITHELIAL_CELL_GROWTH_AND_DEVELOPMENT(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-10", "상피세포의 성장과 발달에 필요", new String[]{"베타카로틴", "비타민 A"}),
    CELL_AND_BLOOD_FORMATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-11", "세포와 혈액 생성에 필요", new String[]{"엽산"}),
    DIETARY_FIBER_SUPPLEMENTATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-12", "식이섬유 보충", new String[]{"식이섬유"}),
    NERVE_AND_MUSCLE_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-13", "신경과 근육 기능 유지에 필요", new String[]{"마그네슘", "칼슘"}),
    NEURODEVELOPMENT(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-14", "신경발달에 필요", new String[]{"요오드"}),
    VISION_ADAPTATION_TO_DARKNESS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-15", "어두운 곳에서 시각 적응을 위해 필요", new String[]{"베타카로틴", "비타민 A"}),
    ENERGY_PRODUCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-16", "에너지 생성에 필요", new String[]{"요오드", "철"}),
    ENERGY_UTILIZATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-17", "에너지 이용에 필요", new String[]{"마그네슘", "망간"}),
    CARBOHYDRATE_FAT_PROTEIN_METABOLISM(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-18", "지방, 탄수화물, 단백질 대사와 에너지 생성에 필요", new String[]{"단백질"}),
    ANTIOXIDANT_PROTECTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-19", "유해산소로부터 세포를 보호하는데 필요", new String[]{"구리", "망간", "셀레늄(또는 셀렌)"}),
    NORMAL_IMMUNE_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-20", "정상적인 면역 기능에 필요", new String[]{"아연"}),
    NORMAL_CELL_DIVISION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-21", "정상적인 세포분열에 필요", new String[]{"아연"}),
    NORMAL_FOLIC_ACID_METABOLISM(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-22", "정상적인 엽산 대사에 필요", new String[]{"비타민 B12"}),
    NORMAL_BLOOD_CLOTTING(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-23", "정상적인 혈액 응고에 필요", new String[]{"비타민 K", "칼슘"}),
    MACRONUTRIENT_METABOLISM(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-24", "지방, 탄수화물, 단백질 대사와 에너지 생성에 필요", new String[]{"비오틴", "판토텐산"}),
    IRON_TRANSPORT_AND_UTILIZATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-25", "철의 운반과 이용에 필요", new String[]{"구리"}),
    IRON_ABSORPTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-26", "철의 흡수에 필요", new String[]{"비타민 C"}),
    FLUID_AND_ELECTROLYTE_BALANCE(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-27", "체내 물과 전해질 균형에 필요", new String[]{"칼륨", "마그네슘", "칼슘", "염화나트륨", "칼륨염"}),
    MUCOUS_MEMBRANE_HEALTH(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-28", "점막 건강에 필요", new String[]{"비타민 A"}),
    SKIN_HEALTH(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-29", "피부 건강에 필요", new String[]{"비타민 A"}),
    COLLAGEN_FORMATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-30", "콜라겐 형성에 필요", new String[]{"비타민 C"}),
    HYDROGEN_PEROXIDE_METABOLISM(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-31", "과산화수소 대사에 필요", new String[]{"셀레늄(또는 셀렌)"}),
    METABOLISM_OF_IRON_AND_VITAMIN_A(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-32", "철과 비타민 A 대사에 필요", new String[]{"비타민 A"}),
    IMMUNE_SYSTEM_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-33", "면역 체계 기능에 필요", new String[]{"비타민 A"}),
    PROTECTION_OF_CELLS_FROM_OXIDATIVE_DAMAGE(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-34", "세포를 산화적 손상으로부터 보호하는데 필요", new String[]{"비타민 C", "비타민 E", "셀레늄(또는 셀렌)"}),
    PROTECTION_AGAINST_FREE_RADICALS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-35", "자유라디칼로부터 세포를 보호하는데 필요", new String[]{"비타민 C", "비타민 E"}),
    PROTECTION_AGAINST_OXIDATIVE_STRESS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-36", "산화적 스트레스로부터 세포를 보호하는데 필요", new String[]{"비타민 E"}),
    NORMAL_NEUROLOGICAL_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-37", "정상적인 신경 기능에 필요", new String[]{"비타민 B1", "비타민 B6", "비타민 B12", "비오틴", "나이아신"}),
    PSYCHOLOGICAL_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-38", "정상적인 심리 기능에 필요", new String[]{"비타민 B1", "비타민 B6", "비타민 B12", "비오틴", "나이아신"}),
    CARDIOVASCULAR_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-39", "정상적인 심혈관 기능에 필요", new String[]{"비타민 B1"}),
    REDUCTION_OF_TIREDNESS_AND_FATIGUE(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-40", "피로와 피곤함 감소에 필요", new String[]{"비타민 B2", "비타민 B6", "비타민 B12", "판토텐산", "나이아신"}),
    ELECTROLYTE_BALANCE(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-41", "전해질 균형에 필요", new String[]{"칼륨", "마그네슘", "칼슘", "염화나트륨"}),
    METABOLISM_OF_CARBOHYDRATES_AND_MACRONUTRIENTS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-42", "탄수화물과 대사물질 대사에 필요", new String[]{"칼륨", "마그네슘"}),
    METABOLISM_OF_PROTEINS_AND_MACRONUTRIENTS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-43", "단백질과 대사물질 대사에 필요", new String[]{"비오틴"}),
    PROTEIN_SYNTHESIS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-44", "단백질 합성에 필요", new String[]{"비타민 B6"}),
    DNA_SYNTHESIS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-45", "DNA 합성에 필요", new String[]{"비타민 B9"}),
    CELL_DIVISION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-46", "세포분열에 필요", new String[]{"비타민 B12"}),
    NORMAL_BLOOD_PRESSURE(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-47", "정상적인 혈압에 필요", new String[]{"칼륨"}),
    NORMAL_MUSCLE_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-48", "정상적인 근육 기능에 필요", new String[]{"마그네슘"}),
    ELECTROLYTE_BALANCE_IN_MUSCLES(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-49", "근육 내 전해질 균형에 필요", new String[]{"마그네슘"}),
    OSMOTIC_PRESSURE_REGULATION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-50", "삼투압 조절에 필요", new String[]{"마그네슘"}),
    NORMAL_COGNITIVE_FUNCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-51", "정상적인 인지 기능에 필요", new String[]{"철", "아연"}),
    NORMAL_VISION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-52", "정상적인 시력에 필요", new String[]{"비타민 A"}),
    MAINTENANCE_OF_NORMAL_VISION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-53", "정상적인 시력 유지에 필요", new String[]{"비타민 A"}),
    NORMAL_SKIN(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-54", "정상적인 피부에 필요", new String[]{"비타민 A"}),
    NORMAL_HAIR(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-55", "정상적인 모발에 필요", new String[]{"비타민 A"}),
    NORMAL_NAIL(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-56", "정상적인 손톱에 필요", new String[]{"비타민 A"}),
    PROTECTION_AGAINST_OXIDATIVE_DAMAGE_TO_CELLS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-57", "세포의 산화적 손상으로부터 보호에 필요", new String[]{"비타민 E"}),
    ANTIOXIDANT_CAPACITY(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-58", "항산화 능력에 필요", new String[]{"비타민 E"}),
    PROTECTION_AGAINST_OXIDATIVE_STRESS_TO_CELLS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-59", "세포의 산화적 스트레스로부터 보호에 필요", new String[]{"비타민 E"}),
    PROTECTION_AGAINST_FREE_RADICALS_TO_CELLS(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-60", "세포의 자유라디칼로부터 보호에 필요", new String[]{"비타민 E"}),
    NORMAL_FERTILITY_AND_REPRODUCTION(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-61", "정상적인 생식과 생식 기능에 필요", new String[]{"비타민 E"}),
    NORMAL_REPRODUCTIVE_DEVELOPMENT(FunctionalityCategory.NUTRITIONAL_FUNCTION, "2-62", "정상적인 생식 발달에 필요", new String[]{"비타민 E"}),
    INTESTINAL_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-1", "장 건강"),
    BLOOD_SUGAR_CONTROL(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-2", "혈당 조절"),
    JOINT_BONE_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-3", "관절/뼈 건강"),
    CHOLESTEROL_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-4", "혈중 콜레스테롤 개선"),
    BODY_FAT_REDUCTION(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-5", "체지방 감소"),
    IMMUNE_FUNCTION_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-6", "면역기능개선"),
    ANTIOXIDATION(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-7", "항산화"),
    SKIN_HEALTH_2(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-8", "피부 건강"),
    BLOOD_PRESSURE_CONTROL(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-9", "혈압 조절"),
    BLOOD_NEUTRAL_FAT_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-10", "혈중 중성지방 개선"),
    BLOOD_CIRCULATION_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-11", "혈행개선"),
    MEMORY_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-12", "기억력 개선"),
    LIVER_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-13", "간 건강"),
    EYE_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-14", "눈 건강"),
    RELAXATION(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-15", "긴장완화"),
    COGNITIVE_SKILL_ENHANCEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-16", "인지능력 향상"),
    PROSTATE_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-17", "전립선 건강"),
    CALCIUM_ABSORPTION_PROMOTION(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-18", "칼슘 흡수 촉진"),
    EXERCISE_PERFORMANCE(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-19", "운동수행 능력"),
    URINARY_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-20", "요로 건강"),
    TOOTH_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-21", "치아 건강"),
    FATIGUE_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-22", "피로개선"),
    MENOPAUSE_MALE_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-23", "갱년기 남성 건강"),
    MENOPAUSE_FEMALE_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-24", "갱년기 여성 건강"),
    SENSITIVE_SKIN_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-25", "과민 피부 상태 개선"),
    URINATION_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-26", "배뇨기능개선"),
    STOMACH_HEALTH_DIGESTIVE_FUNCTION(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-27", "위 건강/소화기능"),
    SPERM_MOTILITY(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-28", "정자 운동성"),
    FEMALE_VAGINAL_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-29", "여성 질 건강"),
    PREMENSTRUAL_SYNDROME_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-30", "월경전 상태 개선"),
    CHILD_GROWTH_DEVELOPMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-31", "어린이 성장 발육"),
    SLEEP_QUALITY_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-32", "수면질 개선"),
    MUSCLE_STRENGTH_IMPROVEMENT(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-33", "근력 개선"),
    ORAL_HEALTH(FunctionalityCategory.BIOLOGICAL_ACTIVITY, "3-34", "구강 건강");

    private final FunctionalityCategory category;
    private final String id, label;
    private final String[] nutrients;

    FunctionalityType(FunctionalityCategory category, String id, String label) {
        this(category, id, label, null);
    }

    FunctionalityType(FunctionalityCategory category, String id, String label, String[] nutrients) {
        this.category = category;
        this.id = id;
        this.label = label;
        this.nutrients = nutrients;
    }

    public FunctionalityCategory getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String[] getNutrients() {
        return nutrients;
    }
}
