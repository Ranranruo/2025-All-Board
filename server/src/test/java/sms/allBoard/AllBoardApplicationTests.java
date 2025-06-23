package sms.allBoard;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sms.allBoard.Common.Domain.Member.Model.Member;
import sms.allBoard.Common.Domain.Member.Repository.MemberRepository;
import sms.allBoard.Common.Util.Redis.StringRedisUtil;

import java.util.List;

@SpringBootTest
class AllBoardApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(AllBoardApplicationTests.class);
	@Autowired
    private MemberRepository memberRepository;
    @Autowired
    private StringRedisUtil stringRedisUtil;

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


}
