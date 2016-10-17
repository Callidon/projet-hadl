package com.alma.hadl.metamodel.configuration;

import com.alma.hadl.metamodel.Element;
import com.alma.hadl.metamodel.IObserver;
import com.alma.hadl.metamodel.interfaces.Interface;
import com.alma.hadl.metamodel.interfaces.provided.Provided;
import com.alma.hadl.metamodel.interfaces.provided.ProvidedPort;
import com.alma.hadl.metamodel.interfaces.required.Required;
import com.alma.hadl.metamodel.interfaces.required.RequiredPort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Minier
 * @date 17/10/16
 */
public abstract class Configuration extends Element {
    protected List<Element> elements;
    protected List<IObserver> links;

    public Configuration(List<Interface> interfaces) {
        super(interfaces);
        links = new ArrayList<>();
        elements = new ArrayList<>();
    }

    public void addElement(Element element) {
        elements.add(element);
    }

    public <T> void attach(Provided<T> input, final Required<T> output) {
        input.addObserver(new IObserver<T>() {
            public void update(T data) {
                output.receive(data);
            }
        });
    }

    public <T> void bind(ProvidedPort<T> left, final ProvidedPort<T> right) {
        left.addObserver(new IObserver<T>() {
            public void update(T data) {
                right.notifyObservers(data);
            }
        });
    }

    public <T> void bind(RequiredPort<T> left, final RequiredPort<T> right) {
        left.addObserver(new IObserver<T>() {
            public void update(T data) {
                right.receive(data);
            }
        });
    }
}
