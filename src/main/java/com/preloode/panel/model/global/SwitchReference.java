package com.preloode.panel.model.global;

import com.preloode.panel.enumeration.global.SwitchStatus;
import org.springframework.data.mongodb.core.index.Indexed;


public class SwitchReference {


    @Indexed
    private SwitchStatus blog;

    @Indexed
    private SwitchStatus shop;


    public SwitchReference() {


    }


    public SwitchReference(SwitchStatus blog, SwitchStatus shop) {

        this.blog = blog;
        this.shop = shop;

    }


    public SwitchStatus getBlog() {

        return blog;

    }


    public void setBlog(SwitchStatus blog) {

        this.blog = blog;

    }


    public SwitchStatus getShop() {

        return shop;

    }


    public void setShop(SwitchStatus shop) {

        this.shop = shop;

    }


}
