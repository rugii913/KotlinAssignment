package kotlinassignment.week3.guide

import kotlinassignment.week3.flowState.FlowState
import kotlinassignment.week3.menu.MenuGroup
import kotlinassignment.week3.messenger.InputMessenger
import kotlinassignment.week3.messenger.Message

class MenuGroupGuide: Guide {

    override fun guide(flowState: FlowState) {
        // 메뉴 출력에 대한 부분
        val menuGroupEntries = MenuGroup.entries
        flowState.outputMessenger.writeMenuGroup(menuGroupEntries)
        
        // 사용자의 입력 처리에 대한 부분
        val (inputStatus, selectedNumber) = flowState.inputMessenger.readInt()
        if (inputStatus == InputMessenger.InputStatus.ABNORMAL) return // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않음, 다음 명령어들을 실행하지 않기 위해 return
        // TODO 여기서 InputMessenger 내부의 enum을 참조하고 있어서 InputMessenger에 의존함, 의존하지 않을 수 있는 방법?

        if (selectedNumber == 0) {
            flowState.outputMessenger.write(Message.EXIT)
            flowState.nextGuide = null
        } else if (selectedNumber <= menuGroupEntries.size) {
            flowState.nextGuide = MenuItemGuide()
            flowState.nextMenuGroup = menuGroupEntries[selectedNumber - 1]
        } else {
            flowState.outputMessenger.write(Message.NO_CORRESPONDING_SERVICE_NUMBER)
            // 다시 menuGroupGuide의 guide를 호출해야하므로 nextGuide를 set하지 않는다.
        }
    }
}
