# 규칙 14 public 클래스 안에는 public 필드를 두지 말고 접근자 메서드를 사용하라

```
class Point {
     public double x;
     public double y;
}
```

데이터 필드를 직접 조작할 수 있어서 캡슐화의 이점을 누릴수 없다.
API를 변경하지 않고서는 내부 표현을 변경할 수 없고, 불변식(invariant)도 강제할 수 없고, 필드를 사용하는 순간에 어떤 동작이 실행되도록 만들 수도 없다.
객체 지향 개념을 생각하여 변경 가능 클래스라면 수정자(mutator)와 setter도 제공해야 할 것이다.

```
class Point {
     private double x;
     private double y;
     public Point(double x, double y) {
          this.x = x;
          this.y = y;
     }

     public double getX() {}
     public double getY() {}
     public void setX(){}
     public void setY(){}
}
```

`선언된 패키지 밖에서도 사용가능한 클래스에는 접근자 메서드를 제공하라.`
하지만 package-private클래스나 private nested class는 데이터 필드를 공개하더라도 잘못이라 말할 수 없다. (클래스가 추상화하려는 내용을 제대로 기술하기만 한다면)

> package-private는 데이터 필드가 공개여도 상관없음! 이 말인 즉 해당 패키지 내에서만 사용하는경우에 한해서 필드를 공개로 두어도 무방하다는 의미로 생각되며. 실제로 public으로 두는 것이 어떤 문제를 야기 할 수 있겠지만 간단하게 필드에 접근할 수 있어 용이한 듯.

클라이언트 코드가 클래스 내부 표현에 종속된다는 문제가 있긴 하지만, 클라이언트 코드가 같은 패키지 안에 있을 수 밖에 없다는 점을 고려해야 한다. private nested class의 경우 그 클래스 바깥 클래스 외부의 코드는 아무 영향도 받지 않을 것이다.

public 클래스가 내부 필드를 외부로 공개하는 것은 바람직하지 않지만, 변경 불가능(immutable) 필드는 그 심각정이 좀 덜하다. API를 수정하지 않고는 내부 표현을 변경할 수 없고, 필드에 접근하는 순간 어떤 동작이 실행되게 만들 수 없지만, 불변식(invariant)을 강제할 수 있다.

요약: public 클래스는 변경 가능 필드를 외부로 공개하면 안된다. package-private, private로 선언된 nested class의 필드는 외부로 공개하는 것이 바람직할 때도 있다.
