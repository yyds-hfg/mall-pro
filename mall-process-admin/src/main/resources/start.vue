<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px">
      <el-form-item label="手机号" prop="mobile">
        <el-input v-model="formData.mobile" placeholder="请输入手机号" :maxlength="11" show-word-limit clearable
          prefix-icon='el-icon-mobile' :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="计数器" prop="field103">
        <el-input-number v-model="formData.field103" placeholder="计数器"></el-input-number>
      </el-form-item>
      <el-form-item label="单行文本" prop="field101">
        <el-input v-model="formData.field101" placeholder="请输入单行文本" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="滑块" prop="field104" required>
        <el-slider :max='100' :step='1' v-model="formData.field104"></el-slider>
      </el-form-item>
      <el-row gutter="15">
      </el-row>
      <el-form-item label="日期范围" prop="field105">
        <el-date-picker type="daterange" v-model="formData.field105" format="yyyy-MM-dd"
          value-format="yyyy-MM-dd" :style="{width: '100%'}" start-placeholder="开始日期" end-placeholder="结束日期"
          range-separator="至" clearable></el-date-picker>
      </el-form-item>
      <el-form-item label="下拉选择" prop="field102">
        <el-select v-model="formData.field102" placeholder="请选择下拉选择" clearable :style="{width: '100%'}">
          <el-option v-for="(item, index) in field102Options" :key="index" :label="item.label"
            :value="item.value" :disabled="item.disabled"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        mobile: '',
        field103: undefined,
        field101: undefined,
        field104: 0,
        field105: null,
        field102: undefined,
      },
      rules: {
        mobile: [{
          required: true,
          message: '请输入手机号',
          trigger: 'blur'
        }, {
          pattern: /^1(3|4|5|7|8|9)\d{9}$/,
          message: '手机号格式错误',
          trigger: 'blur'
        }],
        field103: [{
          required: true,
          message: '计数器',
          trigger: 'blur'
        }],
        field101: [{
          required: true,
          message: '请输入单行文本',
          trigger: 'blur'
        }],
        field105: [{
          required: true,
          message: '日期范围不能为空',
          trigger: 'change'
        }],
        field102: [{
          required: true,
          message: '请选择下拉选择',
          trigger: 'change'
        }],
      },
      field102Options: [{
        "label": "选项一",
        "value": 1
      }, {
        "label": "选项二",
        "value": 2
      }],
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (!valid) return
        // TODO 提交表单
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
