package dev.sosasosa.basements.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getServer;

public class TestHandler implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        getServer().broadcastMessage("§2[Server] 끝없는 동대문구청의 서버에 온 것을 환영한다, §4" + event.getPlayer().getName() + ".");
    }

    @EventHandler
    public void getPlayerCmd(AsyncPlayerChatEvent event) {
        //Player player = event.getPlayer();

        if (event.getMessage().equals("공지")) {
            getServer().broadcastMessage("""
                    §2< 공지 > 230209 04:43AM
                    - 심포니하우스 102호 마을의 모든 광물 이동완료
                    - 심포니하우스 103호 '대장장이의 방' 입주완료
                      특징 : 방에 용광로가 많음
                    - '주민 철도 이송 계획' 하달
                    * 채팅으로 '공지' 입력 시 공지 다시보기 기능 추가 완료
                      §f이용 가능한 입력 : '공지', '주민 철도 이송 계획'""");
        }

        if (event.getMessage().equals("주민 철도 이송 계획")) {
            getServer().broadcastMessage("""
                    §2[주민 철도 이송 계획] 하달
                                    
                      §f목적 : 철 공장 강제 노역, 학업 지원 및 인첸트 개발 장려
                      철도 절도 실패로 철블럭 1세트반, 금블럭 반세트 광질
                                    
                      * '수선' 교환 주민 마을 (1250, 77, 550)
                      * 가장 가까운 주민 생존 마을 발견! (700, ???, 300)
                      * 주민 멸망한 우리 마을 위치 (150, 70. 300)
                      => 철블럭 반세트 이상 및 금, 레드스톤으로 민영화 철도 운영 재개가능""");
        }
    }
}
