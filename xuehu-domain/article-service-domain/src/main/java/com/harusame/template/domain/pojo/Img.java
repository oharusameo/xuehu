package com.harusame.template.domain.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 图片表
 * @TableName t_img
 */
@TableName(value ="t_img")
@Data
public class Img implements Serializable {
    /**
     * 
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 图片名称
     */
    @TableField(value = "img_name")
    private String imgName;

    /**
     * 图片哈希值
     */
    @TableField(value = "img_hash")
    private String imgHash;

    /**
     * 原图的完整访问路径
     */
    @TableField(value = "big_img_full_path")
    private String bigImgFullPath;

    /**
     * 原图的短路径
     */
    @TableField(value = "big_img_sub_path")
    private String bigImgSubPath;

    /**
     * 缩略图的完整访问路径
     */
    @TableField(value = "small_img_full_path")
    private String smallImgFullPath;

    /**
     * 缩略图的短路径
     */
    @TableField(value = "small_img_sub_path")
    private String smallImgSubPath;

    /**
     * 图片归属的用户
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 图片的创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 图片的创建人
     */
    @TableField(value = "create_user")
    private Integer createUser;

    /**
     * 记录该图片被引用的数量
     */
    @TableField(value = "ref_count")
    private Integer refCount;

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
        Img other = (Img) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getImgName() == null ? other.getImgName() == null : this.getImgName().equals(other.getImgName()))
            && (this.getImgHash() == null ? other.getImgHash() == null : this.getImgHash().equals(other.getImgHash()))
            && (this.getBigImgFullPath() == null ? other.getBigImgFullPath() == null : this.getBigImgFullPath().equals(other.getBigImgFullPath()))
            && (this.getBigImgSubPath() == null ? other.getBigImgSubPath() == null : this.getBigImgSubPath().equals(other.getBigImgSubPath()))
            && (this.getSmallImgFullPath() == null ? other.getSmallImgFullPath() == null : this.getSmallImgFullPath().equals(other.getSmallImgFullPath()))
            && (this.getSmallImgSubPath() == null ? other.getSmallImgSubPath() == null : this.getSmallImgSubPath().equals(other.getSmallImgSubPath()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getRefCount() == null ? other.getRefCount() == null : this.getRefCount().equals(other.getRefCount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getImgName() == null) ? 0 : getImgName().hashCode());
        result = prime * result + ((getImgHash() == null) ? 0 : getImgHash().hashCode());
        result = prime * result + ((getBigImgFullPath() == null) ? 0 : getBigImgFullPath().hashCode());
        result = prime * result + ((getBigImgSubPath() == null) ? 0 : getBigImgSubPath().hashCode());
        result = prime * result + ((getSmallImgFullPath() == null) ? 0 : getSmallImgFullPath().hashCode());
        result = prime * result + ((getSmallImgSubPath() == null) ? 0 : getSmallImgSubPath().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getRefCount() == null) ? 0 : getRefCount().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", imgName=").append(imgName);
        sb.append(", imgHash=").append(imgHash);
        sb.append(", bigImgFullPath=").append(bigImgFullPath);
        sb.append(", bigImgSubPath=").append(bigImgSubPath);
        sb.append(", smallImgFullPath=").append(smallImgFullPath);
        sb.append(", smallImgSubPath=").append(smallImgSubPath);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", refCount=").append(refCount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}