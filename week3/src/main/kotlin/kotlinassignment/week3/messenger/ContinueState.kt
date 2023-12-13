package kotlinassignment.week3.messenger

import kotlinassignment.week3.guide.FrontGuide
import kotlinassignment.week3.guide.Guide

class ContinueState {

     /*
     TODO 사용자의 메뉴 input에 따라 KioskMain이 적절한 Guide의 적절한 메서드를 호출하도록 어떤 상태를 저장하게 할 때, 그 상태 저장과 처리를 이 ContinueState에서 하게 할지? 고민
          - 일단 현재는 이 ContinueState에서 처리하게 한 상태
          - 원래는 utility 모듈에 있었으나, 부적절함 - utility 모듈에 있는 코드는 week3에 있는 코드에 의존해서는 안 된다.
            - 그러므로 Guide 같은 타입을 알아야 한다면 utility가 될 수 없다.
          - week3 모듈 내에서 어떤 패키지에 넣을지 고민 중, 일단은 messenger 패키지에 넣었으나, 적절하지 않은 것 같다.
     */
    var nextGuide: Guide? = FrontGuide()
    var previousGuide: Guide = FrontGuide()
}