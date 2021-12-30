package ithings.backend.repository;

import ithings.backend.domain.MemberBasic;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public MemberBasic save(MemberBasic member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("MEMBERBASIC").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }
    @Override
    public Optional<MemberBasic> findById(Long id) {
        List<MemberBasic> result = jdbcTemplate.query("select * from MEMBERBASIC  where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }
    @Override
    public List<MemberBasic> findAll() {
        return jdbcTemplate.query("select * from MEMBERBASIC ", memberRowMapper());
    }
    @Override
    public Optional<MemberBasic> findByName(String name) {
        List<MemberBasic> result = jdbcTemplate.query("select * from MEMBERBASIC  where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }
    private RowMapper<MemberBasic> memberRowMapper() {
        return (rs, rowNum) -> {
            MemberBasic member = new MemberBasic();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}
