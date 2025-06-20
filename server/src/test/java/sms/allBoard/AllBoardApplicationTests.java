package sms.allBoard;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sms.allBoard.Common.Domain.Member.Member;
import sms.allBoard.Common.Domain.Member.MemberRepository;
import sms.allBoard.Common.Util.RedisUtil;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class AllBoardApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(AllBoardApplicationTests.class);
	@Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RedisUtil redisUtil;

	@Test
	void contextLoads() {
	}

	@Test
	void test() {
//		Optional<Member> member = memberRepository.findByUsername("tlsalstjr58");
//		memberRepository.delete(member.get());
		List<Member> list = memberRepository.findAll();
		System.out.println(list);
		log.info("true");
	}

	@Test
	void test1() {
		String a = redisUtil.get("asdasda");

		log.info(a);
	}

}
