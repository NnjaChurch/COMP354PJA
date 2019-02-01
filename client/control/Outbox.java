package control;

import model.CardType;

import java.util.Observable;
import java.util.Observer;

public class Outbox implements Observer {

    // Attributes
    Reply mReply;

    @Override
    public void update(Observable o, Object arg) {

    }
}
