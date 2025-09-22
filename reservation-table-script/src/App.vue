<template>
	<div id="app">
		<div class="tableBox">
			<el-form ref="form" :model="formData" label-width="130px">
				<el-form-item label="微信用户ID：">
					<el-input v-model="formData.wx_session_user_id" placeholder="请输入微信用户ID" clearable></el-input>
				</el-form-item>
				<el-form-item label="服务ID：">
					<el-input v-model="formData.service_id" disabled></el-input>
				</el-form-item>
				<el-form-item label="预约人姓名：">
					<el-input v-model="formData.service_record.ekcmdfazkbhm" placeholder="请输入预约人姓名"
						clearable></el-input>
				</el-form-item>
				<el-form-item label="请选择预约日期：">
					<el-date-picker type="date" placeholder="选择日期" v-model="formData.service_record.wx_reserve_date"
						style="width: 100%;"></el-date-picker>
				</el-form-item>
				<el-form-item label="请选择预约时段：">
					<el-checkbox-group v-model="reserveTimes">
						<!-- 第一行 -->
						<el-row>
							<el-col :span="6"><el-checkbox label="7.30-8.30" name="type"></el-checkbox></el-col>
							<el-col :span="6"><el-checkbox label="8.30-10.30" name="type"></el-checkbox></el-col>
							<el-col :span="6"><el-checkbox label="10.30-12.00" name="type"></el-checkbox></el-col>
							<el-col :span="6"><el-checkbox label="12-13" name="type"></el-checkbox></el-col>
						</el-row>
						<!-- 第二行 -->
						<el-row>
							<el-col :span="6"><el-checkbox label="13-15" name="type"></el-checkbox></el-col>
							<el-col :span="6"><el-checkbox label="15-17" name="type"></el-checkbox></el-col>
							<el-col :span="6"><el-checkbox label="17-19" name="type"></el-checkbox></el-col>
							<el-col :span="6"><el-checkbox label="19-21" name="type"></el-checkbox></el-col>
						</el-row>
						<!-- 第三行 -->
						<el-row>
							<el-col :span="6"><el-checkbox label="21-22" name="type"></el-checkbox></el-col>
							<el-col :span="6"><el-checkbox label="22-23" name="type"></el-checkbox></el-col>
						</el-row>
					</el-checkbox-group>
				</el-form-item>
				<el-form-item label="任务启动时间段：">
					<el-time-picker is-range v-model="taskTime" range-separator="至" start-placeholder="开始时间"
						end-placeholder="结束时间" placeholder="选择时间范围">
					</el-time-picker>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="onSubmit">开始任务</el-button>
					<el-button>重置参数</el-button>
				</el-form-item>
			</el-form>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'App',
		data() {
			return {
				formData: {
					// 服务id
					service_id: "68bafee6156d84e54d84ae55",
					// 预约参数
					service_record: {},
				},
				// 预约时段 -- 到时候再转化
				reserveTimes: [],
				// 任务时间段
				taskTime: [new Date(2016, 9, 10, 8, 40), new Date(2016, 9, 10, 9, 40)]
			}
		},
		methods: {
			onSubmit() {
				console.info('填写请求参数表单数据===>',this.formData)
				console.info('填写请求参数表单数据===>',this.reserveTimes)
				console.info('填写请求参数表单数据===>',this.taskTime)
			}
		}
	}
</script>

<style>

	.tableBox {
		/* 黑色实线边框 */
		border: 2px solid #55aaff;
		/* 上下边距20px，左右自动居中 */
		margin: 20px auto;
		/* 内边距让内容不贴边 */
		padding: 20px;
		/* 限制宽度为父容器的80% */
		width: 70%;
		/* 最大宽度限制（可选） */
		max-width: 800px;
		/* 边框和内边距包含在宽度内 */
		box-sizing: border-box;
	}
</style>