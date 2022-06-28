package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

public class Hello {

    public Name function(){
        Name name = new Name();
        name.setName("sdf");
        return name;
    }

    @Getter @Setter
    static class Name{
        private String name;
    }
}
