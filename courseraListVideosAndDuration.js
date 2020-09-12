//for planning schedules
let tasks = document.getElementsByClassName("rc-WeekSingleItemDisplay")
let outstring = ''
for(let i = 0; i<tasks.length; i++){
  let currentTask = tasks[i].getElementsByClassName("rc-WeekItemName")[0].innerText
  currentTask = currentTask.split("\n")
  currentTask = currentTask[currentTask.length-1]
   //optional reading skip
  if(currentTask.startsWith("Reading")) continue;
  outstring+= currentTask
  let duration = tasks[i].getElementsByClassName("rc-EffortText")
  if(duration.length) duration = duration[0].getElementsByTagName("span")
  else if(currentTask.startsWith("Review")) outstring+="\t"+"20"
  if(duration.length>1){
    duration = duration[1].innerText.split(" ")[0]
    if(duration[duration.length-1]!=="h")outstring+="\t"+duration
    else{
      outstring+="\t"+duration.substring(0, duration.length-1)*60
    }
  }

  else if(duration.length==1) outstring+="\t"+duration[0].innerText.split(" ")[0]
  outstring+='\n'
}
console.log(outstring)
