package dev.sosasosa.basements.chat;

import java.util.List;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.Random;
import java.util.Timer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BasementsChat extends JavaPlugin {
    Logger logger = getServer().getLogger();
    Timer timerForAnnouncement = new Timer();
    Timer timerForWhisper = new Timer();

    @Override
    public void onEnable() {
        super.onEnable();
        logger.info("Plugin Activated!");

        getServer().getPluginManager().registerEvents(new TestHandler(), this);
        logger.info("TestHandler : Activated!");

        timerForAnnouncement.schedule(new TimerTask() {
            @Override
            public void run() {
                sendAnnouncement();
            }
        }, 5000, 500000);
        timerForWhisper.schedule(new TimerTask() {
            @Override
            public void run() {
                sendWhisper();
            }
        }, 5000, 100000);
    }

    @Override
    public void onLoad() {
        logger.info("PLUG-IN : onLoad()");
    }

    @Override
    public void onDisable() {
        logger.info("Plugin Inactivated!");
    }

    /**
     * Gong-ji for you.
     */
    public void sendAnnouncement() {
        getServer().broadcastMessage("""
                §2< 공지 > 230209 04:43AM
                - 심포니하우스 102호 마을의 모든 광물 이동완료
                - 심포니하우스 103호 '대장장이의 방' 입주완료
                  특징 : 방에 용광로가 많음
                - '주민 철도 이송 계획' 하달
                * 채팅으로 '공지' 입력 시 공지 다시보기 기능 추가 완료
                  §f이용 가능한 입력 : '공지', '주민 철도 이송 계획'
                """);
        /*
        Random random = new Random(System.currentTimeMillis());
        logger.info("PLUG-IN : get number '" + random + "'.");
        switch (random.nextInt(3)) {
            case 0 -> getServer().broadcastMessage("§0Message -1-");
            case 1 -> getServer().broadcastMessage("§1Message -2-");
            case 2 -> getServer().broadcastMessage("§2Message -3-");
            default -> getServer().broadcastMessage("§fUnpredictable Number");
        }
         */
    }

    /**
     * Bring your ears.
     */
    public void sendWhisper() {
        List<Player> playerList = (List<Player>)getServer().getOnlinePlayers();

        if (playerList.size() == 0) {
            return;
        }

        logger.info("PLUG-IN : playerList.size() : " + playerList.size());

        Random random = new Random(System.currentTimeMillis());

        int playerNumber = random.nextInt(playerList.size());
        playerList.get(playerNumber).sendMessage("속삭속삭!");

        /*
        playerList.get(playerNumber).sendMessage("""
                < 공지 > 230209 04:43AM
                         
                - 심포니하우스 102호 마을의 모든 광물 이동완료
                - 심포니하우스 103호 '대장장이의 방' 입주완료
                  특징 : 방에 용광로가 많음
                         
                - [주민 철도 이송 계획]
                  목적 : 철 공장 강제 노역, 학업 지원 및 인첸트 개발 장려
                  철도 절도 실패로 철블럭 1세트반, 금블럭 반세트 광질
                         
                  * '수선' 교환 주민 마을 위치 (1250, 77, 550)
                  * 가장 가까운 주민 생존 마을 발견! (700, ???, 300)
                  * 주민 멸망한 우리 마을 위치 (150, 70. 300)
                         
                  => 철블럭 반세트 이상 및 금, 레드스톤으로 민영화 철도 운영 재개가능""");
         */
        /*
        playerList.get(playerNumber).sendMessage("""
                내 몸속에 일본놈들의 총알이 여섯개나 박혀있습니다.
                일천구백십이년 경성에서 데라우치 총독 암살 때 총맞은 자립니다. 구멍이 두개지요.
                여긴 이십이년 상해 황포탄에서 이십칠년 하바로프스크에서, 삽십이년 이치구폭파사건때. 이 심장 옆은 삼십삼년에.
                내가 동지 셋을 팔았다고 하셨는데! 그 친구들 제가 직접 뽑았습니다.

                그 젊은 청춘들이 어떤 심정이었는지 아십니까? 여러분들은 모릅니다! 내가 어떤 심정으로 그들을 보냈는지!
                그건! 죽음을 불사하는 항전의 거름이었습니다 재판장님!
                ...이상입니다.""");
         */
    }

}
