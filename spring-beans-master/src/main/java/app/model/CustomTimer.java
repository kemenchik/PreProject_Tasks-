package app.model;


import org.springframework.stereotype.Component;

@Component
public class CustomTimer extends Timer {

    @Override
    public Long getTime() {
        return super.getTime();
    }
}
