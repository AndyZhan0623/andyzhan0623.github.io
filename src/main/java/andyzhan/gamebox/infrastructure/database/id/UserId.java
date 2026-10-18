package andyzhan.gamebox.infrastructure.database.id;

public record UserId(Long value){

    public static UserId fromString(String id){
        return new UserId(Long.parseLong(id));
    }

}
