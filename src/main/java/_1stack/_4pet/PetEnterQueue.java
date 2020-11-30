package _1stack._4pet;

public class PetEnterQueue {

    private Pet pet;
    private long count;

    // pet是实例，count是时间戳
    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public String getEnterPetType() {
        return this.pet.getPetType();
    }
}
