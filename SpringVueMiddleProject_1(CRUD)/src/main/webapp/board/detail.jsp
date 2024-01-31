<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://code.jquery.com/jquery.js"></script>
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
		<h3 class="text-center">내용보기</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th width="20%" class="text-center">번호</th>
					<td width="30%" class="text-center">{{board_detail.no}}</td>
					<th width="20%" class="text-center">작성일</th>
					<td width="30%" class="text-center">{{board_detail.dbday}}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">이름</th>
					<td width="30%" class="text-center">{{board_detail.name}}</td>
					<th width="20%" class="text-center">조회수</th>
					<td width="30%" class="text-center">{{board_detail.hit}}</td>
				</tr>
				<tr>
					<th width="20%" class="text-center">제목</th>
					<td colspan="3">{{board_detail.subject}}</td>
				</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top">
						<pre style="white-space: pre-wrap;">{{board_detail.content}}</pre>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<input type="button" value="수정" class="btn btn-xs btn-success" @click="update()">
						<input type="button" value="삭제" class="btn btn-xs btn-info" @click="del()" ref="delBtn">
						<input type="button" value="목록" class="btn btn-xs btn-warning" @click="listData()">
					</td>
				</tr>
				<!-- <tr id="del" style="display: none;">
					<td colspan="4" class="text-right">
						비밀번호:<input type="password" size="10" class="input-sm" ref="pwd">
						<input type="button" class="btn btn-sm btn-success" value="삭제">
					</td>
				</tr> -->
				<tr v-show="isShow">
					<td colspan="4" class="text-right">
						비밀번호:<input type="password" size="10" class="input-sm" ref="pwd">
						<input type="button" class="btn btn-sm btn-success" value="삭제" @click="boardDelete()">
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		let app=Vue.createApp({
			data(){
				return{
					board_detail:{},
					no:${no},
					change:0,
					isShow:false
				}
			},
			mounted(){
				axios.get('../board/detail_vue.do',{
					params:{
						no:this.no
					}
				}).then(response=>{
					this.board_detail=response.data
					console.log(response.data)
				}).catch(error=>{
					console.log(error.response)
				})
			},
			methods:{
				update(){
					location.href='../board/update.do?no='+this.no
				},
				listData(){
					location.href='../board/list.do'
				},
				del(){
					if(this.change===0){
						this.change=1
						// $('#delBtn').val('취소')
						// $('#del').show()
						this.$refs.delBtn.value='취소'
						this.isShow=true
					}else{
						this.change=0
						// $('#delBtn').val('수정')
						// $('#del').hide()
						this.$refs.delBtn.value='수정'
						this.isShow=false
					}
				},
				boardDelete(){
					let pwd=this.$refs.pwd
					if(pwd.value===''){
						pwd.focus()
						return
					}
					axios.get('../board/delete_vue.do',{
						params:{
							no:this.no,
							pwd:pwd.value
						}
					}).then(response=>{
						if(response.data==='yes'){
							location.href='../board/list.do'
						}else{
							alert('비밀번호가 틀립니다')
							pwd.value=''
							pwd.focus()
						}
					}).catch(error=>{
						console.log(error.response)
					})
				}
			}
		}).mount('.container')
	</script>
</body>
</html>