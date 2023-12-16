package com.harusame.template.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import com.harusame.template.enums.CreateCategoryEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 分类表
 * @TableName t_category
 */
@TableName(value ="t_category",autoResultMap = true)
@Data
public class Category implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 图片分类的名称
     */
    @TableField(value = "category_name")
    private String categoryName;

    /**
     * 该分类包含图片id，以json数组形式保存
     */
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<Integer> imageIds;

    /**
     * 分类类型,1:系统创建,2:用户创建
     */
    @TableField(value = "category_type")
    private CreateCategoryEnum categoryType;

    /**
     * 分类归属的用户
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 分类的创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 分类的更新时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 分类的创建人，系统创建则取值为0
     */
    @TableField(value = "create_user")
    private Integer createUser;

    /**
     * 分类的修改人，系统修改则取值为0
     */
    @TableField(value = "update_user")
    private Integer updateUser;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Category other = (Category) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
            && (this.getImageIds() == null ? other.getImageIds() == null : this.getImageIds().equals(other.getImageIds()))
            && (this.getCategoryType() == null ? other.getCategoryType() == null : this.getCategoryType().equals(other.getCategoryType()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getImageIds() == null) ? 0 : getImageIds().hashCode());
        result = prime * result + ((getCategoryType() == null) ? 0 : getCategoryType().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", imageIds=").append(imageIds);
        sb.append(", categoryType=").append(categoryType);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}