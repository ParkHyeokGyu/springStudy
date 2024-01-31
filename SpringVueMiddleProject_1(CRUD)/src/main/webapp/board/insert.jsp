<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.container{
		margin-top: 50px;
	}
	.row{
		margin: 0px auto;
		width: 700px;
	}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">글쓰기</h3>
		<div class="row">
			<!-- prevent : submit이나 a태그의 href는 먼저 기능을 수행한다(데이터 전송을 먼저 수행) -->
			<!-- 기능 수행을 취소하고 submitForm메소드를 호출하여 처리한다 -->
			<form @submit.prevent="submitForm">
			<table class="table">
				<tr>
					<th width="15%" class="text-center">이름</th>
					<td width="85%">
						<input type="text" size="15" class="input-sm" v-model="name" ref="name">
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-center">제목</th>
					<td width="85%">
						<input type="text" size="50" class="input-sm" v-model="subject" ref="subject">
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-center">내용</th>
					<td width="85%">
						<textarea rows="10" cols="52" v-model="content" ref="content"></textarea>
					</td>
				</tr>
				<tr>
					<th width="15%" class="text-center">비밀번호</th>
					<td width="85%">
						<input type="password" size="15" class="input-sm" v-model="pwd" ref="pwd">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type="submit" value="글쓰기" class="btn btn-sm btn-success">
						<input type="button" value="취소" class="btn btn-sm btn-info" onclick="javascript:history.back()">
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					name:'',
					subject:'',
					content:'',
					pwd:''
				}
			},
			methods:{
				submitForm(){
					if(this.name===''){
						this.$refs.name.focus()
						return
					}
					if(this.subject===''){
						this.$refs.subject.focus()
						return
					}
					if(this.content===''){
						this.$refs.content.focus()
						return
					}
					if(this.pwd===''){
						this.$refs.pwd.focus()
						return
					}
					
					axios.post('../board/insert_ok.do',null,{
						params:{
							name:this.name,
							subject:this.subject,
							content:this.content,
							pwd:this.pwd
						}
					}).then(response=>{
						location.href='../board/list.do'
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount('.container')
	</script>
</body>
</html>