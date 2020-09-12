//for planning

let tasks = document.getElementsByClassName("rc-WeekSingleItemDisplay")
let outstring = ''
for(let i = 0; i<tasks.length; i++){
  let currentTask = tasks[i].getElementsByClassName("rc-WeekItemName")[0].innerText
  console.log(i)
  console.log(currentTask)
  currentTask = currentTask.split("\n")
  console.log(currentTask)
  outstring+= currentTask[currentTask.length-1]
  let duration = tasks[i].getElementsByClassName("rc-EffortText")
  if(duration.length) duration = duration[0].getElementsByTagName("span")
  if(duration.length>1) outstring+="\t"+duration[1].innerText.split(" ")[0]
  else if(duration.length==1) outstring+="\t"+duration[0].innerText.split(" ")[0]/2
  outstring+='\n'
}
console.log(outstring)
