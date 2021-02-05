package entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author al
 * @date 2021/2/5 10:47
 * @description
 */
@Entity
@Table(name = "sys_dictionary", schema = "test")
public class SysDictionaryEntity {
    private long id;
    private String type;
    private String code;
    private String content;
    private String contentEn;
    private String contentJp;
    private String pattern;
    private String remark;
    private String appKey;
    private Byte deleted;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 50)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "code", nullable = true, length = 50)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 100)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "content_en", nullable = true, length = 100)
    public String getContentEn() {
        return contentEn;
    }

    public void setContentEn(String contentEn) {
        this.contentEn = contentEn;
    }

    @Basic
    @Column(name = "content_jp", nullable = true, length = 100)
    public String getContentJp() {
        return contentJp;
    }

    public void setContentJp(String contentJp) {
        this.contentJp = contentJp;
    }

    @Basic
    @Column(name = "pattern", nullable = true, length = 100)
    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 100)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Basic
    @Column(name = "app_key", nullable = true, length = 125)
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Basic
    @Column(name = "deleted", nullable = true)
    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDictionaryEntity that = (SysDictionaryEntity) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(code, that.code) &&
                Objects.equals(content, that.content) &&
                Objects.equals(contentEn, that.contentEn) &&
                Objects.equals(contentJp, that.contentJp) &&
                Objects.equals(pattern, that.pattern) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(appKey, that.appKey) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, code, content, contentEn, contentJp, pattern, remark, appKey, deleted);
    }
}
